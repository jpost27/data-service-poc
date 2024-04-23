package com.jp.dataservicepoc.data;

import com.jp.dataservicepoc.repository.base.JPRepository;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.EntityPathBase;
import jakarta.persistence.Entity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class PersistenceMapping {

    private static final String ENTITY_PACKAGE = "com.jp.dataservicepoc.model.entity";
    private static final String DTO_PACKAGE = "com.jp.dataservicepoc.model.dto";
    private static final String REPO_PACKAGE = "com.jp.dataservicepoc.repository";
    public static final String ENTITY_CLASS_PREFIX = "Fd";

    private final Map<String, EntityMetadata<?, ?, ?, ?>> entityNameToEntityMetadata;
    private final Map<String, EntityMetadata<?, ?, ?, ?>> dtoNameToEntityMetadata;
    private final Map<String, EntityMetadata<?, ?, ?, ?>> queryNameToEntityMetadata;
    // FdTeam.class -> FdTeamColor.class -> OneToMany.class
    @Getter private final Map<Class<?>, Map<String, EntityConnection>> entityRelationshipMap;

    public PersistenceMapping(List<JPRepository> repositories) {

        final Map<String, EntityMetadata<?, ?, ?, ?>> entityNameToEntityMetadata = new HashMap<>();
        final Map<String, EntityMetadata<?, ?, ?, ?>> dtoNameToEntityMetadata = new HashMap<>();
        final Map<String, EntityMetadata<?, ?, ?, ?>> queryNameToEntityMetadata = new HashMap<>();

        final Set<Class<?>> entityPackageClasses = findAllClassesUsingClassLoader(ENTITY_PACKAGE);
        // "FdTeam" -> FdTeam.class
        final Map<String, Class<?>> stringToEntityClassMap = entityPackageClasses.stream()
                .filter(clazz -> Arrays.stream(clazz.getAnnotations())
                        .anyMatch(annotation -> annotation.annotationType().isAssignableFrom(Entity.class)))
                .peek(clazz -> {
                    if (!clazz.getSimpleName().startsWith(ENTITY_CLASS_PREFIX)) {
                        throw new IllegalArgumentException(
                                "Entity classes must start with common prefix: " + ENTITY_CLASS_PREFIX + ". Class "
                                        + clazz.getSimpleName() + " breaks this convention.");
                    }
                })
                .collect(Collectors.toMap(Class::getSimpleName, Function.identity()));
        // "QFdTeam" -> QFdTeam.class
        final Map<String, Class<?>> stringToQClassMap = entityPackageClasses.stream()
                .filter(EntityPathBase.class::isAssignableFrom)
                .collect(Collectors.toMap(Class::getSimpleName, Function.identity()));
        // "Team" -> Team.class
        final Map<String, Class<?>> stringToDtoClassMap = findAllClassesUsingClassLoader(DTO_PACKAGE).stream()
                .collect(Collectors.toMap(Class::getSimpleName, Function.identity()));

        repositories.forEach(repository -> {
            // "com.jp.dataservicepoc.repository.TeamRepository"
            String repositoryName = Arrays.stream(repository.getClass().getGenericInterfaces())
                    .map(Type::getTypeName)
                    .filter(typeName -> typeName.startsWith(REPO_PACKAGE))
                    .findFirst()
                    .orElse(null);
            if (repositoryName != null) {
                // "Team"
                String dtoName = repositoryName.substring(
                        repositoryName.lastIndexOf(".") + 1, repositoryName.lastIndexOf("Repository"));
                String queryName = dtoNameToQueryString(dtoName);
                String entityName = dtoNameToEntityName(dtoName);
                Class<?> entityClass = stringToEntityClassMap.get(entityName);
                Class<?> dtoClass = stringToDtoClassMap.get(dtoName);
                Class<?> qClass = stringToQClassMap.get("Q" + entityName);
                EntityPathBase<?> qClassRoot = Arrays.stream(qClass.getDeclaredFields())
                        .filter(field -> qClass.isAssignableFrom(field.getType()))
                        .map(field -> {
                            try {
                                return (EntityPathBase<?>) field.get(null);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .findFirst()
                        .orElseThrow();
                EntityMetadata entityMetadata =
                        createEntityMetadata(queryName, dtoName, dtoClass, entityName, entityClass, qClass, qClassRoot, repository);
                entityNameToEntityMetadata.put(entityName, entityMetadata);
                dtoNameToEntityMetadata.put(dtoName, entityMetadata);
                queryNameToEntityMetadata.put(queryName, entityMetadata);
            }
        });

        final Map<Class<?>, Map<String, EntityConnection>> entityRelationshipMap = new HashMap<>();
        Set<Class<? extends Annotation>> relationshipAnnotations = Set.of(
                OneToOne.class,
                OneToMany.class,
                ManyToMany.class,
                ManyToOne.class
        );
        stringToEntityClassMap.values().forEach(entityClass -> {
            Arrays.stream(entityClass.getDeclaredFields())
                    .forEach(field -> {
                        for (Class<? extends Annotation> relationshipAnnotation : relationshipAnnotations) {
                            if (field.isAnnotationPresent(relationshipAnnotation)) {
                                if (!entityRelationshipMap.containsKey(entityClass)) {
                                    entityRelationshipMap.put(entityClass, new HashMap<>());
                                }
                                EntityConnection connection = new EntityConnection(
                                        entityClass,
                                        field.getName(),
                                        getInnerType(field),
                                        relationshipAnnotation
                                );
                                entityRelationshipMap.get(entityClass).put(field.getName(), connection);
                            }
                        }
                    });
        });

        this.entityNameToEntityMetadata = Collections.unmodifiableMap(entityNameToEntityMetadata);
        this.dtoNameToEntityMetadata = Collections.unmodifiableMap(dtoNameToEntityMetadata);
        this.queryNameToEntityMetadata = Collections.unmodifiableMap(queryNameToEntityMetadata);
        this.entityRelationshipMap = Collections.unmodifiableMap(entityRelationshipMap);
    }

    private Class<?> getInnerType(Field field) {
        TypeVariable<? extends Class<?>>[] innerTypes = field.getType().getTypeParameters();
        if (innerTypes.length == 0) {
            return field.getType();
        } else {
            return (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        }
    }

    // "Team" -> "teams"
    public static String dtoNameToQueryString(String dtoName) {
        return Character.toLowerCase(dtoName.charAt(0))
                + dtoName.substring(1)
                + (dtoName.charAt(dtoName.length() - 1) == 's' ? "es" : "s");
    }

    // "Team" -> "FdTeam"
    public static String dtoNameToEntityName(String dtoName) {
        return ENTITY_CLASS_PREFIX + dtoName;
    }

    public EntityMetadata entityMetadataFromDtoName(String dtoName) {
        return dtoNameToEntityMetadata.get(dtoName);
    }

    public EntityMetadata entityMetadataFromEntityName(String entityName) {
        return entityNameToEntityMetadata.get(entityName);
    }

    public EntityMetadata entityMetadataFromQueryName(String queryName) {
        return queryNameToEntityMetadata.get(queryName);
    }

    public EntityMetadata entityMetadataFromDtoClass(Class<?> dtoClass) {
        return dtoNameToEntityMetadata.get(dtoClass.getSimpleName());
    }

    public EntityMetadata entityMetadataFromEntityClass(Class<?> entityClass) {
        return entityNameToEntityMetadata.get(entityClass.getSimpleName());
    }

    // "teams" -> "fdTeams"
    public static String queryStringToGraphPath(String queryString) {
        return ENTITY_CLASS_PREFIX.toLowerCase()
                + Character.toUpperCase(queryString.charAt(0))
                + queryString.substring(1);
    }

    private Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    private <D, E, Q extends EntityPath<E>, I> EntityMetadata<D, E, Q, I> createEntityMetadata(
            String queryName,
            String dtoName,
            Class<?> dtoClass,
            String entityName,
            Class<?> entityClass,
            Class<?> qClass,
            EntityPathBase<?> qRoot,
            JPRepository repository) {
        return new EntityMetadata<>(
                queryName,
                dtoName,
                dtoClass,
                entityName,
                entityClass,
                (Class<? extends EntityPath>) qClass,
                qRoot,
                repository);
    }

    public String entityFieldNameToDtoFieldName(String fieldName) {
        return Character.toUpperCase(fieldName.charAt(ENTITY_CLASS_PREFIX.length())) +
                fieldName.substring(ENTITY_CLASS_PREFIX.length() + 1);
    }

    public String dtoFieldNameToEntityFieldName(String fieldName) {
        return ENTITY_CLASS_PREFIX.toLowerCase() +
                Character.toUpperCase(fieldName.charAt(0)) +
                fieldName.substring(1);
    }

    public record EntityMetadata<D, E, Q extends EntityPath<E>, I>(
            String queryName,
            String dtoName,
            Class<D> dtoClass,
            String entityName,
            Class<E> entityClass,
            Class<Q> qClass,
            EntityPathBase<?> qRoot,
            JPRepository<D, E, Q, I> repository) {}

    public record EntityConnection(
            Class<?> rootEntityClass,
            String fieldName,
            Class<?> targetEntity,
            Class<? extends Annotation> relationshipType
    ){}
}
