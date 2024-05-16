package com.jp.codegen.generator;

import com.google.common.base.CaseFormat;
import com.jp.codegen.model.GenerationOptions;
import com.jp.codegen.model.TableRelationship;
import com.jp.codegen.model.enums.RelationshipType;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Modifier;
import lombok.extern.slf4j.Slf4j;
import schemacrawler.schema.Column;
import schemacrawler.schema.Table;

@Slf4j
public class JavaDTOGenerator extends SqlTableFileGenerator {

    public JavaDTOGenerator(GenerationOptions generationOptions) {
        super(generationOptions);
    }

    @Override
    protected boolean shouldGenerate(Table table) {
        return options.isGenerateJavaDto();
    }

    @Override
    protected void generate(Table table, Collection<TableRelationship> allTableRelationships) {

        // Initialize DTO spec
        TypeSpec.Builder dtoSpec = TypeSpec.classBuilder(transformTableName(table.getName()));
        dtoSpec.addModifiers(Modifier.PUBLIC);

        // Add column fields
        for (Column column : sortColumns(table.getColumns())) {
            addColumn(dtoSpec, column);
        }
        // Add relationship fields
        Set<RelationshipType> listRelationTypes = Set.of(RelationshipType.ONE_TO_MANY, RelationshipType.MANY_TO_MANY);
        for (TableRelationship relationship : allTableRelationships) {
            addTableRelationship(dtoSpec, relationship, listRelationTypes);
        }

        // Write to outputDirectory
        JavaFile javaFile = JavaFile.builder(options.getJavaDtoPackageName(), dtoSpec.build())
                .indent("    ")
                .build();

        writeJavaFile(javaFile, options.getJavaDtoOutputDirectory());
    }

    private void addColumn(TypeSpec.Builder dtoClass, Column column) {
        String name = transformColumnName(column.getName());
        TypeName type = TypeVariableName.get(column.getType().getTypeMappedClass());
        dtoClass.addField(type, name, Modifier.PRIVATE);
        dtoClass.addMethod(createGetter(name, type));
        dtoClass.addMethod(createSetter(name, type));
    }

    private void addTableRelationship(
            TypeSpec.Builder dtoClass, TableRelationship relationship, Set<RelationshipType> listRelationTypes) {
        String name = transformColumnName(relationship.targetTable().getName());
        TypeName type = TypeVariableName.get(
                transformTableName(relationship.targetTable().getName()));
        if (listRelationTypes.contains(relationship.relationshipType())) {
            type = ParameterizedTypeName.get(ClassName.get(List.class), type);
        }
        dtoClass.addField(type, name, Modifier.PRIVATE);
        dtoClass.addMethod(createGetter(name, type));
        dtoClass.addMethod(createSetter(name, type));
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
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
    }

    private String transformColumnName(String columnName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
    }
}
