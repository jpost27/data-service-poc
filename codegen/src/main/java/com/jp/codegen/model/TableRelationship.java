package com.jp.codegen.model;

import com.jp.codegen.model.enums.RelationshipType;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import schemacrawler.schema.ColumnReference;
import schemacrawler.schema.ForeignKey;
import schemacrawler.schema.Table;
import schemacrawler.schema.TableConstraint;

public record TableRelationship(
        Table rootTable, Table targetTable, Set<ColumnReference> columnReferences, RelationshipType relationshipType) {

    public static Optional<TableRelationship> of(Table rootTable, Table targetTable) {
        RelationshipType relationshipType;
        List<ForeignKey> targetOwningKeyList = getForeignKeysForTable(rootTable, targetTable);
        List<ForeignKey> rootOwningKeyList = getForeignKeysForTable(targetTable, rootTable);
        Set<ColumnReference> columnReferences = new HashSet<>();
        columnReferences.addAll(rootOwningKeyList.stream()
                .flatMap(fk -> fk.getColumnReferences().stream())
                .toList());
        columnReferences.addAll(targetOwningKeyList.stream()
                .flatMap(fk -> fk.getColumnReferences().stream())
                .toList());
        int rootPrimaryKeyCount = rootOwningKeyList.size();
        int targetPrimaryKeyCount = targetOwningKeyList.size();
        boolean allConnectionsArePrimaryKeys = true;
        if (targetTable.getName().equals("team_team_associations")) {
            int x = 5;
        }
        for (ColumnReference columnReference : columnReferences) {
            if (!columnReference.getForeignKeyColumn().isPartOfPrimaryKey()) {
                allConnectionsArePrimaryKeys = false;
                break;
            }
        }
        if (rootTable.getName().equals("team_team_associations")
                || targetTable.getName().equals("team_team_associations")) {
            int x = 5;
        }

        if (allConnectionsArePrimaryKeys) {
            relationshipType = RelationshipType.ONE_TO_ONE;
        } else if (rootPrimaryKeyCount == 1 && targetPrimaryKeyCount == 1) {
            relationshipType = RelationshipType.ONE_TO_ONE;
        } else if (rootPrimaryKeyCount == 0 && targetPrimaryKeyCount > 0) {
            relationshipType = RelationshipType.MANY_TO_ONE;
        } else if (rootPrimaryKeyCount > 0 && targetPrimaryKeyCount == 0) {
            // check for many to many
            List<TableConstraint> targetTableForeignKeys = targetTable.getTableConstraints().stream()
                    .filter(constraint -> constraint instanceof ForeignKey)
                    .toList();
            if (targetTableForeignKeys.size() == targetTable.getColumns().size()
                    && targetTableForeignKeys.size() == 2) {
                // target ManyToMany table is a relationship table. Pursue foreign keys to determine linking table
                ForeignKey linkingTableConstraint = targetTableForeignKeys.stream()
                        .filter(foreignKey -> foreignKey instanceof ForeignKey)
                        .map(foreignKey -> (ForeignKey) foreignKey)
                        .filter(foreignKey -> !foreignKey.getPrimaryKeyTable().equals(rootTable))
                        .findFirst()
                        .orElseThrow();
                targetOwningKeyList = getForeignKeysForTable(linkingTableConstraint.getPrimaryKeyTable(), targetTable);
                rootOwningKeyList = getForeignKeysForTable(rootTable, targetTable);
                columnReferences = new HashSet<>();
                columnReferences.addAll(rootOwningKeyList.stream()
                        .flatMap(fk -> fk.getColumnReferences().stream())
                        .toList());
                columnReferences.addAll(targetOwningKeyList.stream()
                        .flatMap(fk -> fk.getColumnReferences().stream())
                        .toList());
                return Optional.of(new TableRelationship(
                        rootTable,
                        linkingTableConstraint.getPrimaryKeyTable(),
                        columnReferences,
                        RelationshipType.MANY_TO_MANY));
            }
            relationshipType = RelationshipType.ONE_TO_MANY;
        } else if (rootPrimaryKeyCount > 0 && targetPrimaryKeyCount > 0) {
            relationshipType = RelationshipType.ONE_TO_MANY;
        } else {
            relationshipType = RelationshipType.NONE;
        }
        return relationshipType == RelationshipType.NONE
                ? Optional.empty()
                : Optional.of(new TableRelationship(rootTable, targetTable, columnReferences, relationshipType));
    }

    private static List<ForeignKey> getForeignKeysForTable(Table table, Table targetTable) {
        return table.getTableConstraints().stream()
                .filter(constraint -> constraint instanceof ForeignKey)
                .map(constraint -> (ForeignKey) constraint)
                .filter(foreignKey -> foreignKey.getPrimaryKeyTable().getName().equals(targetTable.getName()))
                .toList();
    }
}
