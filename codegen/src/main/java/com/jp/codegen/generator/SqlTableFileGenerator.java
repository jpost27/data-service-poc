package com.jp.codegen.generator;

import com.jp.codegen.model.TableRelationship;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import schemacrawler.schema.Column;
import schemacrawler.schema.Table;

@Slf4j
public abstract class SqlTableFileGenerator {

    /**
     * Generate a file for the given table
     * @param table
     * @param outputDirectory
     */
    public void generate(Table table, File outputDirectory) {
        if (!table.hasPrimaryKey() && table.getColumns().size() < 2) {
            log.warn(
                    "Skipping table {} as it has no primary key and less than 2 columns. Deemed likely to be a sequence table.",
                    table.getFullName());
            return;
        }
        Collection<TableRelationship> allTableRelationships = new ArrayList<>();
        allTableRelationships.addAll(table.getReferencedTables().stream()
                .flatMap(referencedTable -> TableRelationship.between(table, referencedTable).stream())
                .collect(Collectors.toSet()));
        allTableRelationships.addAll(table.getDependentTables().stream()
                .flatMap(dependentTable -> TableRelationship.between(table, dependentTable).stream())
                .collect(Collectors.toSet()));
        generate(table, allTableRelationships, outputDirectory);
    }

    protected abstract void generate(
            Table table, Collection<TableRelationship> allTableRelationships, File outputDirectory);

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
}
