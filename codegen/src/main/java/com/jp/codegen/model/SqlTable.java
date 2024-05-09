package com.jp.codegen.model;

import com.jp.codegen.model.enums.ColumnTypes;
import com.jp.codegen.model.enums.RelationshipType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SqlTable {

    private final String tableName;
    private final Map<String, SqlTable> tableMap;
    private List<ColumnMetadata> columnMetadataList = new ArrayList<>();
    private Set<ForeignKeyMetadata> foreignKeyMetadataSet = new HashSet<>();
    private Set<ForeignKeyMetadata> referecesSet = new HashSet<>();

    public List<ColumnDefinition> getColumnDefinitions() {
        List<ColumnDefinition> columnDefinitions = new ArrayList<>();
        for (ColumnMetadata columnMetadata : columnMetadataList) {
            Optional<ForeignKeyMetadata> fkMetaOptional = foreignKeyMetadataSet.stream()
                    .filter(fk -> fk.getReferencingColumnName().equals(columnMetadata.getColumnName()))
                    .findFirst();
            columnDefinitions.add(new ColumnDefinition(
                    columnMetadata.getOrdinalPosition(),
                    columnMetadata.getColumnName(),
                    ColumnTypes.fromSqlType(columnMetadata.getDataType()),
                    columnMetadata.getIsNullable(),
                    columnMetadata.getCharacterMaximumLength(),
                    "UNI".equalsIgnoreCase(columnMetadata.getColumnKey()),
                    "PRI".equalsIgnoreCase(columnMetadata.getColumnKey()),
                    fkMetaOptional.isPresent(),
                    fkMetaOptional
                            .map(ForeignKeyMetadata::getReferencedTableName)
                            .orElse(null)));
        }
        // Sort by primary key, foreign key, foreign key table name, ordinal position
        columnDefinitions.sort((def1, def2) -> {
            if (def1.primaryKey() != def2.primaryKey()) {
                return def1.primaryKey() ? -1 : 1;
            } else if (def1.foreignKey() != def2.foreignKey()) {
                return def1.foreignKey() ? -1 : 1;
            } else if (def1.foreignKeyTable() != null && def2.foreignKeyTable() != null) {
                return def1.foreignKeyTable().compareTo(def2.foreignKeyTable());
            } else {
                return def1.ordinalPosition().compareTo(def2.ordinalPosition());
            }
        });
        return columnDefinitions;
    }

    public List<TableRelationship> getTableRelationships() {
        List<TableRelationship> tableRelationships = new ArrayList<>();
        for (ForeignKeyMetadata foreignKeyMetadata : foreignKeyMetadataSet) {
            RelationshipType relationshipType = getTableRelationshipType(foreignKeyMetadata);
            Optional<TableRelationship> existingRelationship = tableRelationships.stream()
                    .filter(tr -> tr.targetTable().equals(foreignKeyMetadata.getReferencedTableName()))
                    .findFirst();
            if (existingRelationship.isPresent()) {
                if (existingRelationship
                                .map(TableRelationship::relationshipType)
                                .orElse(null)
                        == relationshipType) {
                    throw new IllegalStateException("Multiple relationship types between tables: " + tableName + " and "
                            + foreignKeyMetadata.getReferencedTableName());
                }
                continue;
            }
            if (relationshipType == RelationshipType.NONE) {
                continue;
            }
            //            tableRelationships.add(new TableRelationship(
            //                    foreignKeyMetadata.getReferencingTableName(),
            //                    foreignKeyMetadata.getReferencingColumnName(),
            //                    foreignKeyMetadata.getReferencedTableName(),
            //                    foreignKeyMetadata.getReferencedKeyName(),
            //                    getTableRelationshipType(foreignKeyMetadata)));
        }
        return tableRelationships;
    }

    private RelationshipType getTableRelationshipType(ForeignKeyMetadata foreignKeyMetadata) {
        if (foreignKeyMetadata.getReferencingTableName().equals(foreignKeyMetadata.getReferencedTableName())) {
            return RelationshipType.NONE;
        }
        if (isPrimaryKeyOrUnique(foreignKeyMetadata.getReferencingColumnName())) {
            if (isPrimaryKeyOrUnique(foreignKeyMetadata.getReferencedKeyName())) {
                return RelationshipType.ONE_TO_ONE;
            }
            return RelationshipType.ONE_TO_MANY;
        } else if (isPrimaryKeyOrUnique(foreignKeyMetadata.getReferencedKeyName())) {
            return RelationshipType.MANY_TO_ONE;
        } else {
            // Neither key is primary key or unique. Most likely a many-to-many relationship.
            // Need to filter out joining of foreign keys
            return RelationshipType.MANY_TO_MANY;
        }
    }

    private boolean isPrimaryKeyOrUnique(String referencingColumnName) {
        return columnMetadataList.stream()
                .anyMatch(column -> column.getColumnName().equals(referencingColumnName)
                        && ("PRI".equalsIgnoreCase(column.getColumnKey())
                                || "UNI".equalsIgnoreCase(column.getColumnKey())));
    }

    private boolean isPrimaryKey(String columnName) {
        return columnMetadataList.stream()
                .anyMatch(column ->
                        column.getColumnName().equals(columnName) && "PRI".equalsIgnoreCase(column.getColumnKey()));
    }
}
