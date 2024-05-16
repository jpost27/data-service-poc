package com.jp.codegen.generator;

import com.jp.codegen.model.GenerationOptions;
import com.jp.codegen.model.TableRelationship;
import com.squareup.javapoet.JavaFile;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import schemacrawler.schema.Column;
import schemacrawler.schema.Table;

@Slf4j
@RequiredArgsConstructor
public abstract class SqlTableFileGenerator {

    protected final GenerationOptions options;

    /**
     * Generate a file for the given table
     *
     * @param table
     */
    public void generate(Table table) {
        if (!table.hasPrimaryKey() && table.getColumns().size() < 2) {
            log.warn(
                    "Skipping table {} as it has no primary key and less than 2 columns. Deemed likely to be a sequence table.",
                    table.getFullName());
            return;
        }
        if (!shouldGenerate(table)) {
            return;
        }
        Collection<TableRelationship> allTableRelationships = new ArrayList<>();
        allTableRelationships.addAll(table.getReferencedTables().stream()
                .flatMap(referencedTable -> TableRelationship.between(table, referencedTable).stream())
                .collect(Collectors.toSet()));
        allTableRelationships.addAll(table.getDependentTables().stream()
                .flatMap(dependentTable -> TableRelationship.between(table, dependentTable).stream())
                .collect(Collectors.toSet()));
        generate(table, allTableRelationships);
    }

    protected abstract boolean shouldGenerate(Table table);

    protected abstract void generate(Table table, Collection<TableRelationship> allTableRelationships);

    protected List<Column> sortColumns(List<Column> columns) {
        List<Column> sortedColumns = new ArrayList<>(columns);
        sortedColumns.sort((c1, c2) -> {
            if (c1.isPartOfPrimaryKey() && !c2.isPartOfPrimaryKey()) {
                return -1;
            } else if (!c1.isPartOfPrimaryKey() && c2.isPartOfPrimaryKey()) {
                return 1;
            } else {
                return Integer.compare(c1.getOrdinalPosition(), c2.getOrdinalPosition());
            }
        });
        return sortedColumns;
    }

    protected void writeJavaFile(JavaFile javaFile, File outputDirectory) {
        try {
            if (log.isTraceEnabled()) {
                javaFile.writeTo(System.out);
            }
            Path path = Paths.get(outputDirectory.toURI());
            javaFile.writeTo(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void writeTextToFile(String text, File outputFile) {
        outputFile.mkdirs();
        if (outputFile.exists()) {
            outputFile.delete();
        }
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(text);
        } catch (Exception e) {
            throw new RuntimeException("Error writing to file: " + outputFile, e);
        }
    }
}
