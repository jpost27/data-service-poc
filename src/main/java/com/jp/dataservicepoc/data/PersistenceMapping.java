package com.jp.dataservicepoc.data;


import com.jp.dataservicepoc.repository.base.JPRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Getter
public class PersistenceMapping {

    private static final String ENTITY_PACKAGE = "com.jp.dataservicepoc.model.entity";
    private static final String DTO_PACKAGE = "com.jp.dataservicepoc.model.dto";
    private static final String ENTITY_CLASS_PREFIX = "Fd";

    private final Map<String, Class<?>> stringToEntityClassMap;
    private final Map<String, Class<?>> queryStringToEntityClassMap;
    private final Map<Class<?>, Class<?>> entityClassToDtoClassMap;
    private final Map<Class<?>, JPRepository<?, ?, ?, ?>> entityClassToRepositoryMap;

    public PersistenceMapping(List<JPRepository> repositories) {
        final Map<Class<?>, JPRepository<?, ?, ?, ?>> entityClassToRepositoryMap = new HashMap<>();

        // Map: 'Team' -> FdTeam.class
        final Map<String, Class<?>> stringToEntityClassMap = findAllClassesUsingClassLoader(ENTITY_PACKAGE)
                .stream()
                .filter(clazz -> !clazz.getSimpleName().startsWith("Q" + ENTITY_CLASS_PREFIX))
                .peek(clazz -> {
                    if (!clazz.getSimpleName().startsWith(ENTITY_CLASS_PREFIX)) {
                        throw new IllegalArgumentException("Entity classes must start with common prefix: " + ENTITY_CLASS_PREFIX + ". Class " + clazz.getSimpleName() + " breaks this convention.");
                    }
                })
                .collect(Collectors.toMap(
                        clazz -> clazz.getSimpleName().substring(ENTITY_CLASS_PREFIX.length()),
                        Function.identity()
                ));
        final Map<String, Class<?>> stringToDtoClassMap = findAllClassesUsingClassLoader(DTO_PACKAGE)
                .stream()
                .collect(Collectors.toMap(
                        Class::getSimpleName,
                        Function.identity()
                ));
        final Map<Class<?>, Class<?>> entityClassToDtoClassMap = stringToDtoClassMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> Optional.ofNullable(stringToEntityClassMap.get(entry.getKey()))
                                .orElseThrow(() -> new NoSuchElementException("Could not find matching Entity for DTO: " + entry.getValue().getSimpleName())),
                        Map.Entry::getValue
                ));

        repositories.forEach(repository -> {
            String repositoryName = Arrays.stream(repository.getClass().getGenericInterfaces())
                    .map(Type::getTypeName)
                    .filter(typeName -> typeName.startsWith("com.jp.dataservicepoc.repository"))
                    .findFirst()
                    .orElse(null);
            String dtoName = repositoryName.substring(repositoryName.lastIndexOf(".") + 1,
                    repositoryName.lastIndexOf("Repository"));
            Class<?> entityClass = stringToEntityClassMap.get(dtoName);
            entityClassToRepositoryMap.put(entityClass, repository);
        });

        this.queryStringToEntityClassMap = stringToEntityClassMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> classNameToQueryString(entry.getKey()),
                        Map.Entry::getValue
                ));

        this.stringToEntityClassMap = Collections.unmodifiableMap(stringToEntityClassMap);
        this.entityClassToDtoClassMap = Collections.unmodifiableMap(entityClassToDtoClassMap);
        this.entityClassToRepositoryMap = Collections.unmodifiableMap(entityClassToRepositoryMap);
    }

    private String classNameToQueryString(String key) {
        return Character.toLowerCase(key.charAt(0))
                + key.substring(1)
                + (key.charAt(key.length() - 1) == 's' ? "es" : "s");
    }

    public Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
