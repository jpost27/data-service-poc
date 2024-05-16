package com.jp.codegen.generator;

import com.google.common.base.CaseFormat;
import com.jp.codegen.model.GenerationOptions;
import com.jp.codegen.model.TableRelationship;
import com.jp.codegen.model.enums.RelationshipType;
import com.jp.codegen.util.CaseUtil;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import jakarta.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import javax.lang.model.element.Modifier;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import schemacrawler.schema.Column;
import schemacrawler.schema.ColumnReference;
import schemacrawler.schema.NamedObject;
import schemacrawler.schema.Table;

@Slf4j
public class JPAEntityGenerator extends SqlTableFileGenerator {

    private final String entityPackageName = "com.jp.codegen.entity";

    public JPAEntityGenerator(GenerationOptions generationOptions) {
        super(generationOptions);
    }

    @Override
    protected boolean shouldGenerate(Table table) {
        return options.isGenerateJpaEntity();
    }

    @Override
    protected void generate(Table table, Collection<TableRelationship> allTableRelationships) {
        // Initialize JPA entity spec
        TypeSpec.Builder entitySpecBuilder = TypeSpec.classBuilder(transformTableName(table.getName()));

        // Add class-level specifications
        entitySpecBuilder.addModifiers(Modifier.PUBLIC);
        entitySpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.Entity.class)
                .addMember("name", "$S", table.getName())
                .build());
        entitySpecBuilder.addAnnotation(AnnotationSpec.builder(ToString.class).build());
        entitySpecBuilder.addAnnotation(
                AnnotationSpec.builder(EqualsAndHashCode.class).build());

        // Generate JPA entity id class if the table has a composite primary key
        Optional<TypeSpec> entityIdSpecOptional = createEntityIdClass(table, entitySpecBuilder);

        // Add fields for each column
        for (Column column : sortColumns(table.getColumns())) {
            addFieldForColumn(entitySpecBuilder, column);
        }
        // Add relationships to other tables
        Set<RelationshipType> listRelationTypes = Set.of(RelationshipType.ONE_TO_MANY, RelationshipType.MANY_TO_MANY);
        for (TableRelationship relationship : allTableRelationships) {
            addTableRelationship(entitySpecBuilder, relationship, listRelationTypes);
        }

        // Write to outputDirectory
        JavaFile entityJavaFile = buildJavaFile(entitySpecBuilder.build());
        Optional<JavaFile> entityIdJavaFileOptional = entityIdSpecOptional.map(this::buildJavaFile);

        writeJavaFile(entityJavaFile, options.getJpaEntityOutputDirectory());
        entityIdJavaFileOptional.ifPresent(javaFile -> writeJavaFile(javaFile, options.getJpaEntityOutputDirectory()));
    }

    private JavaFile buildJavaFile(TypeSpec build) {
        return JavaFile.builder(entityPackageName, build).indent("    ").build();
    }

    private Optional<TypeSpec> createEntityIdClass(Table table, TypeSpec.Builder entityClass) {
        TypeSpec.Builder entityIdClass = null;
        if (table.getPrimaryKey().getConstrainedColumns().size() > 1) {
            entityClass.addAnnotation(AnnotationSpec.builder(jakarta.persistence.IdClass.class)
                    .addMember("value", "$T.class", TypeVariableName.get(transformTableName(table.getName()) + "Id"))
                    .build());
            entityIdClass = TypeSpec.classBuilder(transformTableName(table.getName()) + "Id");
            entityIdClass.addModifiers(Modifier.PUBLIC);
            entityIdClass.addAnnotation(EqualsAndHashCode.class);
            entityIdClass.addAnnotation(ToString.class);
            for (Column column : table.getPrimaryKey().getConstrainedColumns()) {
                String name = transformColumnName(column.getName(), false);
                TypeName type = TypeVariableName.get(column.getType().getTypeMappedClass());
                FieldSpec.Builder fieldSpecBuilder = FieldSpec.builder(type, name, Modifier.PRIVATE);
                fieldSpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.Column.class)
                        .addMember("name", "$S", column.getName())
                        .build());
                entityIdClass.addField(fieldSpecBuilder.build());
                entityIdClass.addMethod(createGetter(name, type));
                entityIdClass.addMethod(createSetter(name, type));
            }
        }
        return Optional.ofNullable(entityIdClass).map(TypeSpec.Builder::build);
    }

    private void addFieldForColumn(TypeSpec.Builder entityClass, Column column) {

        String name = transformColumnName(column.getName(), false);
        TypeName type = TypeVariableName.get(column.getType().getTypeMappedClass());
        FieldSpec.Builder fieldSpecBuilder = FieldSpec.builder(type, name, Modifier.PRIVATE);
        if (column.isPartOfPrimaryKey()) {
            fieldSpecBuilder.addAnnotation(
                    AnnotationSpec.builder(jakarta.persistence.Id.class).build());
            fieldSpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.GeneratedValue.class)
                    .addMember("strategy", "$T.IDENTITY", jakarta.persistence.GenerationType.class)
                    .build());
        }
        AnnotationSpec.Builder columnAnnotation =
                AnnotationSpec.builder(jakarta.persistence.Column.class).addMember("name", "$S", column.getName());
        if (!column.isNullable()) {
            columnAnnotation.addMember("nullable", "$L", false);
        }
        if (column.isPartOfUniqueIndex()) {
            columnAnnotation.addMember("unique", "$L", true);
        }
        if (column.isPartOfForeignKey()) {
            columnAnnotation.addMember("insertable", "$L", false);
            columnAnnotation.addMember("updatable", "$L", false);
        }
        fieldSpecBuilder.addAnnotation(columnAnnotation.build());
        if (column.isNullable()) {
            fieldSpecBuilder.addAnnotation(Nullable.class);
        }
        entityClass.addField(fieldSpecBuilder.build());
        entityClass.addMethod(createGetter(name, type));
        entityClass.addMethod(createSetter(name, type));
    }

    private void addTableRelationship(
            TypeSpec.Builder entityClass, TableRelationship relationship, Set<RelationshipType> listRelationTypes) {
        String name = transformColumnName(relationship.targetTable().getName(), true);
        TypeName type = TypeVariableName.get(
                transformTableName(relationship.targetTable().getName()));
        if (listRelationTypes.contains(relationship.relationshipType())) {
            type = ParameterizedTypeName.get(ClassName.get(List.class), type);
        }
        FieldSpec.Builder fieldSpecBuilder = FieldSpec.builder(type, name, Modifier.PRIVATE);

        switch (relationship.relationshipType()) {
            case ONE_TO_ONE -> addOneToOneRelationshipAnnotations(relationship, fieldSpecBuilder);
            case ONE_TO_MANY -> addOneToManyRelationshipAnnotations(relationship, fieldSpecBuilder);
            case MANY_TO_ONE -> addManyToOneRelationshipAnnotations(relationship, fieldSpecBuilder);
            case MANY_TO_MANY -> addManyToManyRelationshipAnnotations(relationship, fieldSpecBuilder);
        }
        fieldSpecBuilder.addAnnotation(ToString.Exclude.class);
        fieldSpecBuilder.addAnnotation(EqualsAndHashCode.Exclude.class);

        entityClass.addField(fieldSpecBuilder.build());
        entityClass.addMethod(createGetter(name, type));
        entityClass.addMethod(createSetter(name, type));
    }

    private void addOneToOneRelationshipAnnotations(
            TableRelationship relationship, FieldSpec.Builder fieldSpecBuilder) {
        ColumnReference columnReference =
                relationship.columnReferences().iterator().next();
        if (columnReference.getPrimaryKeyColumn().getParent().equals(relationship.rootTable())) {
            // OneToOne parent side
            fieldSpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.OneToOne.class)
                    .addMember("fetch", "$T.LAZY", jakarta.persistence.FetchType.class)
                    .addMember(
                            "mappedBy",
                            "$S",
                            transformColumnName(relationship.rootTable().getName(), true))
                    .addMember("optional", "$L", false)
                    .build());
            fieldSpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.PrimaryKeyJoinColumn.class)
                    .build());
        } else {
            // OneToOne child side
            fieldSpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.OneToOne.class)
                    .addMember("fetch", "$T.LAZY", jakarta.persistence.FetchType.class)
                    .build());
            fieldSpecBuilder.addAnnotation(
                    AnnotationSpec.builder(jakarta.persistence.MapsId.class).build());
            // @JoinColumn(name = "team_id", nullable = false)
            fieldSpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.JoinColumn.class)
                    .addMember(
                            "name", "$S", columnReference.getForeignKeyColumn().getName())
                    .build());
        }
    }

    private void addOneToManyRelationshipAnnotations(
            TableRelationship relationship, FieldSpec.Builder fieldSpecBuilder) {
        fieldSpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.OneToMany.class)
                .addMember("fetch", "$T.LAZY", jakarta.persistence.FetchType.class)
                .addMember(
                        "mappedBy",
                        "$S",
                        transformColumnName(relationship.rootTable().getName(), true))
                .build());
    }

    private void addManyToOneRelationshipAnnotations(
            TableRelationship relationship, FieldSpec.Builder fieldSpecBuilder) {
        fieldSpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.ManyToOne.class)
                .addMember("fetch", "$T.LAZY", jakarta.persistence.FetchType.class)
                .build());
        fieldSpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.JoinColumn.class)
                .addMember(
                        "name",
                        "$S",
                        relationship.columnReferences().stream()
                                .filter(joinColumn -> joinColumn
                                        .getPrimaryKeyColumn()
                                        .getParent()
                                        .equals(relationship.targetTable()))
                                .map(joinColumn ->
                                        joinColumn.getForeignKeyColumn().getName())
                                .findFirst()
                                .orElseThrow())
                .build());
    }

    private void addManyToManyRelationshipAnnotations(
            TableRelationship relationship, FieldSpec.Builder fieldSpecBuilder) {
        fieldSpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.ManyToMany.class)
                .addMember("fetch", "$T.LAZY", jakarta.persistence.FetchType.class)
                .build());
        Table joinTable = relationship.columnReferences().stream()
                .filter(columnReference ->
                        columnReference.getPrimaryKeyColumn().getParent().equals(relationship.rootTable()))
                .findFirst()
                .orElseThrow()
                .getForeignKeyColumn()
                .getParent();
        List<String> joinColumnNames = joinTable.getForeignKeys().stream()
                .filter(foreignKey -> foreignKey.getPrimaryKeyTable().equals(relationship.rootTable()))
                .flatMap(foreignKey -> foreignKey.getConstrainedColumns().stream())
                .map(NamedObject::getName)
                .toList();
        List<String> inverseColumnNames = joinTable.getForeignKeys().stream()
                .filter(foreignKey -> foreignKey.getPrimaryKeyTable().equals(relationship.targetTable()))
                .flatMap(foreignKey -> foreignKey.getConstrainedColumns().stream())
                .map(NamedObject::getName)
                .toList();
        fieldSpecBuilder.addAnnotation(AnnotationSpec.builder(jakarta.persistence.JoinTable.class)
                .addMember("name", "$S", joinTable.getName())
                .addMember(
                        "joinColumns",
                        "{"
                                + String.join(
                                        ", ",
                                        IntStream.range(0, joinColumnNames.size())
                                                .mapToObj(i -> "$L")
                                                .toArray(String[]::new))
                                + "}",
                        joinColumnNames.stream()
                                .map(columnName -> AnnotationSpec.builder(jakarta.persistence.JoinColumn.class)
                                        .addMember("name", "$S", columnName)
                                        .build())
                                .toArray())
                .addMember(
                        "inverseJoinColumns",
                        "{"
                                + String.join(
                                        ", ",
                                        IntStream.range(0, inverseColumnNames.size())
                                                .mapToObj(i -> "$L")
                                                .toArray(String[]::new))
                                + "}",
                        inverseColumnNames.stream()
                                .map(columnName -> AnnotationSpec.builder(jakarta.persistence.JoinColumn.class)
                                        .addMember("name", "$S", columnName)
                                        .build())
                                .toArray())
                .build());
    }

    private MethodSpec createGetter(String fieldName, TypeName fieldType) {
        return MethodSpec.methodBuilder("get" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, fieldName))
                .addModifiers(Modifier.PUBLIC)
                .returns(fieldType)
                .addStatement("return $N", fieldName)
                .build();
    }

    private MethodSpec createSetter(String fieldName, TypeName fieldType) {
        return MethodSpec.methodBuilder("set" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, fieldName))
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(fieldType, fieldName)
                .addStatement("this.$N = $N", fieldName, fieldName)
                .build();
    }

    private String transformTableName(String tableName) {
        return CaseUtil.convert(options.getJpaEntityClassPrefix(), CaseFormat.UPPER_CAMEL)
                + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
    }

    private String transformColumnName(String columnName, boolean isEntity) {
        if (isEntity) {
            return CaseUtil.convert(options.getJpaEntityClassPrefix(), CaseFormat.LOWER_CAMEL)
                    + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, columnName);
        }
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
    }
}
