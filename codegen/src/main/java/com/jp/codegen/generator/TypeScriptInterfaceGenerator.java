package com.jp.codegen.generator;

import com.google.common.base.CaseFormat;
import com.jp.codegen.model.GenerationOptions;
import com.jp.codegen.model.TableRelationship;
import com.jp.codegen.model.enums.ColumnTypes;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import schemacrawler.schema.Column;
import schemacrawler.schema.Table;

public class TypeScriptInterfaceGenerator extends SqlTableFileGenerator {
    public TypeScriptInterfaceGenerator(GenerationOptions generationOptions) {
        super(generationOptions);
    }

    @Override
    protected boolean shouldGenerate(Table table) {
        return options.isGenerateTypeScript();
    }

    @Override
    public void generate(Table table, Collection<TableRelationship> allTableRelationships) {

        // Generate JPA entity class
        StringBuilder sb = new StringBuilder();

        // Add imports for referenced tables
        importRelatedTables(sb, allTableRelationships);

        // Add interface definition
        sb.append("export interface ")
                .append(transformTableName(table.getName()))
                .append(" {\n");

        // Add column definitions
        addColumnDefinitions(sb, sortColumns(table.getColumns()));

        // Add relationship definitions
        addRelationshipDefinitions(sb, allTableRelationships);

        sb.append("}\n");

        // Write to outputDirectory
        File outputFile = new File(options.getTypeScriptOutputDirectory(), transformTableName(table.getName()) + ".ts");
        // Write to outputFile
        writeTextToFile(sb.toString(), outputFile);
    }

    private void importRelatedTables(StringBuilder sb, Collection<TableRelationship> allTableRelationships) {
        allTableRelationships.stream()
                .map(TableRelationship::targetTable)
                .map(Table::getName)
                .map(this::transformTableName)
                .collect(Collectors.toSet())
                .forEach(referencedTableName -> {
                    sb.append("import { ")
                            .append(referencedTableName)
                            .append(" } from './")
                            .append(referencedTableName)
                            .append("';\n");
                });
    }

    private void addColumnDefinitions(StringBuilder sb, List<Column> columns) {
        for (Column column : columns) {
            sb.append("    ").append(transformColumnName(column.getName()));
            if (column.isNullable()) {
                sb.append("?");
            }
            sb.append(": ");
            switch (ColumnTypes.fromSqlType(column.getType().getDatabaseSpecificTypeName())) {
                case INTEGER, BIG_DECIMAL, BIG_INT -> sb.append("number");
                case VARCHAR -> sb.append("string");
                case BOOLEAN -> sb.append("boolean");
                case DATE, DATE_TIME -> sb.append("Date");
                default -> throw new IllegalArgumentException(
                        "Unsupported column type: " + column.getType().getDatabaseSpecificTypeName());
            }
            sb.append(";\n");
        }
    }

    private void addRelationshipDefinitions(StringBuilder sb, Collection<TableRelationship> allTableRelationships) {
        for (TableRelationship relationship : allTableRelationships) {
            sb.append("    ")
                    .append(transformColumnName(relationship.targetTable().getName()));
            sb.append("?: ");
            switch (relationship.relationshipType()) {
                case ONE_TO_ONE, MANY_TO_ONE -> sb.append(
                        transformTableName(relationship.targetTable().getName()));
                case ONE_TO_MANY, MANY_TO_MANY -> sb.append(
                                transformTableName(relationship.targetTable().getName()))
                        .append("[]");
                case NONE -> throw new IllegalArgumentException(
                        "Unsupported relationship type: " + relationship.relationshipType());
            }
            sb.append(";\n");
        }
    }

    private String transformTableName(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
    }

    private String transformColumnName(String columnName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
    }
}
