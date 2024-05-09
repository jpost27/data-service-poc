package com.jp.codegen.generator;

import com.google.common.base.CaseFormat;
import com.jp.codegen.model.ColumnDefinition;
import com.jp.codegen.model.ForeignKeyMetadata;
import com.jp.codegen.model.SqlTable;
import com.jp.codegen.model.TableRelationship;
import java.io.File;
import java.io.FileWriter;
import java.util.stream.Collectors;

public class TypeScriptInterfaceGenerator implements SqlTableFileGenerator {
    @Override
    public void generate(SqlTable table, File outputDirectory) {
        // Generate JPA entity class
        StringBuilder sb = new StringBuilder();
        table.getTableRelationships().stream()
                .map(TableRelationship::targetTableName)
                .map(this::transformTableName)
                .collect(Collectors.toSet())
                .forEach(targetTableName -> {
                    sb.append("import { ")
                            .append(targetTableName)
                            .append(" } from './")
                            .append(targetTableName)
                            .append("';\n");
                });
        sb.append("export interface ")
                .append(transformTableName(table.getTableName()))
                .append(" {\n");
        for (ColumnDefinition column : table.getColumnDefinitions()) {
            sb.append("    ").append(transformColumnName(column.name()));
            if (column.nullable()) {
                sb.append("?");
            }
            sb.append(": ");
            switch (column.type()) {
                case INTEGER, BIG_DECIMAL, BIG_INT -> sb.append("number");
                case VARCHAR -> sb.append("string");
                case BOOLEAN -> sb.append("boolean");
                case DATE -> sb.append("Date");
                default -> throw new IllegalArgumentException("Unsupported column type: " + column.type());
            }
            sb.append(";\n");
        }
        for (ForeignKeyMetadata foreignKeyMetadata : table.getForeignKeyMetadataSet()) {
            sb.append("    ").append(transformColumnName(foreignKeyMetadata.getReferencedTableName()));
            sb.append(": ");
            sb.append(transformTableName(foreignKeyMetadata.getReferencedTableName()));
            sb.append(";\n");
        }
        sb.append("}\n");

        // Write to outputDirectory
        outputDirectory.mkdirs();
        File outputFile = new File(outputDirectory, transformTableName(table.getTableName()) + ".ts");
        // Write to outputFile
        if (outputFile.exists()) {
            outputFile.delete();
        }
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(sb.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error writing to file: " + outputFile, e);
        }
    }

    private String transformTableName(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
    }

    private String transformColumnName(String columnName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
    }
}
