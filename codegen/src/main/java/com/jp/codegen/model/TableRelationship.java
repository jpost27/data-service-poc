package com.jp.codegen.model;

import com.google.common.collect.Streams;
import com.jp.codegen.model.enums.RelationshipType;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import schemacrawler.schema.Column;
import schemacrawler.schema.ColumnReference;
import schemacrawler.schema.ForeignKey;
import schemacrawler.schema.Table;
import schemacrawler.schema.TableConstraint;

public record TableRelationship(
        Table rootTable, Table targetTable, Set<ColumnReference> columnReferences, RelationshipType relationshipType) {

    public static Optional<TableRelationship> between(Table rootTable, Table targetTable) {
        RelationshipType relationshipType;
        List<ForeignKey> targetOwningKeyList = getForeignKeysLinkingToTable(rootTable, targetTable);
        List<ForeignKey> rootOwningKeyList = getForeignKeysLinkingToTable(targetTable, rootTable);
        Set<ColumnReference> allColumnReferences = Streams.concat(
                        rootOwningKeyList.stream(), targetOwningKeyList.stream())
                .flatMap(fk -> fk.getColumnReferences().stream())
                .collect(Collectors.toSet());

        relationshipType =
                getRelationshipType(targetTable, rootOwningKeyList, targetOwningKeyList, allColumnReferences);
        if (relationshipType == RelationshipType.MANY_TO_MANY) {
            return Optional.of(getManyToManyRelationship(rootTable, targetTable));
        }
        return relationshipType == RelationshipType.NONE
                ? Optional.empty()
                : Optional.of(new TableRelationship(rootTable, targetTable, allColumnReferences, relationshipType));
    }

    private static RelationshipType getRelationshipType(
            Table targetTable,
            List<ForeignKey> rootOwningKeyList,
            List<ForeignKey> targetOwningKeyList,
            Set<ColumnReference> allColumnReferences) {
        int rootPrimaryKeyCount = rootOwningKeyList.size();
        int targetPrimaryKeyCount = targetOwningKeyList.size();
        boolean allConnectionsArePrimaryKeys = true;

        for (ColumnReference columnReference : allColumnReferences) {
            if (!columnReference.getForeignKeyColumn().isPartOfPrimaryKey()) {
                allConnectionsArePrimaryKeys = false;
                break;
            }
        }
        if (allConnectionsArePrimaryKeys) {
            return RelationshipType.ONE_TO_ONE;
        } else if (rootPrimaryKeyCount == 1 && targetPrimaryKeyCount == 1) {
            return RelationshipType.ONE_TO_ONE;
        } else if (rootPrimaryKeyCount == 0 && targetPrimaryKeyCount > 0) {
            return RelationshipType.MANY_TO_ONE;
        } else if (rootPrimaryKeyCount > 0 && targetPrimaryKeyCount == 0) {
            // check for many to many
            if (targetTable.getForeignKeys().size() > 1
                    && !targetTable.hasPrimaryKey()
                    && targetTable.getColumns().stream().allMatch(Column::isPartOfForeignKey)) {
                // all columns of target table are foreign keys linking 2 tables. This is a many to many relationship
                // with another table
                return RelationshipType.MANY_TO_MANY;
            }
            return RelationshipType.ONE_TO_MANY;
        } else if (rootPrimaryKeyCount > 0) {
            return RelationshipType.ONE_TO_MANY;
        } else {
            return RelationshipType.NONE;
        }
    }

    private static TableRelationship getManyToManyRelationship(Table rootTable, Table targetTable) {
        List<TableConstraint> targetTableForeignKeys = targetTable.getTableConstraints().stream()
                .filter(constraint -> constraint instanceof ForeignKey)
                .toList();
        ForeignKey linkingTableConstraint = targetTableForeignKeys.stream()
                .filter(foreignKey -> foreignKey instanceof ForeignKey)
                .map(foreignKey -> (ForeignKey) foreignKey)
                .filter(foreignKey -> !foreignKey.getPrimaryKeyTable().equals(rootTable))
                .findFirst()
                .orElseThrow();
        Table joiningTable = targetTable;
        targetTable = linkingTableConstraint.getPrimaryKeyTable();
        List<ForeignKey> targetOwningKeyList = getForeignKeysLinkingToTable(joiningTable, targetTable);
        List<ForeignKey> rootOwningKeyList = getForeignKeysLinkingToTable(joiningTable, rootTable);
        Set<ColumnReference> columnReferences = Streams.concat(rootOwningKeyList.stream(), targetOwningKeyList.stream())
                .flatMap(fk -> fk.getColumnReferences().stream())
                .collect(Collectors.toSet());
        return new TableRelationship(
                rootTable,
                linkingTableConstraint.getPrimaryKeyTable(),
                columnReferences,
                RelationshipType.MANY_TO_MANY);
    }

    private static List<ForeignKey> getForeignKeysLinkingToTable(Table fkTable, Table targetTable) {
        return fkTable.getTableConstraints().stream()
                .filter(constraint -> constraint instanceof ForeignKey)
                .map(constraint -> (ForeignKey) constraint)
                .filter(foreignKey -> foreignKey.getPrimaryKeyTable().getName().equals(targetTable.getName()))
                .toList();
    }
}
