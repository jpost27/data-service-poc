package com.jp.codegen;

import com.jp.codegen.model.ColumnMetadata;
import com.jp.codegen.model.ForeignKeyMetadata;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SQLMetadataRepo {

    private static final List<String> ignoredTables = List.of(
            "BATCH_JOB_EXECUTION",
            "BATCH_JOB_EXECUTION_CONTEXT",
            "BATCH_JOB_EXECUTION_PARAMS",
            "BATCH_STEP_EXECUTION",
            "BATCH_STEP_EXECUTION_CONTEXT",
            "BATCH_JOB_INSTANCE",
            "BATCH_JOB_EXECUTION_SEQ",
            "BATCH_JOB_SEQ",
            "BATCH_STEP_EXECUTION_SEQ");
    private static final String schemaName = "sport_data";

    private final Connection connection;

    public List<ColumnMetadata> getColumnMetadata() throws SQLException {

        ResultSet resultSet;
        PreparedStatementGenerator columnMetaPs = new PreparedStatementGenerator(
                """
                SELECT * FROM INFORMATION_SCHEMA.COLUMNS
                WHERE TABLE_SCHEMA = ?
                AND TABLE_NAME NOT IN (""");
        columnMetaPs.addValues(schemaName);
        columnMetaPs.addVariables(ignoredTables.size());
        columnMetaPs.addValues(ignoredTables.toArray());
        columnMetaPs.appendSql(");");

        PreparedStatement preparedStatement = columnMetaPs.generatePreparedStatement(connection);
        resultSet = preparedStatement.executeQuery();

        List<ColumnMetadata> columnMetadataList = new ArrayList<>();
        while (resultSet.next()) {
            // Map to ColumnMetadata
            ColumnMetadata columnMetadata = new ColumnMetadata();
            columnMetadata.setTableCatalog(resultSet.getString("TABLE_CATALOG"));
            columnMetadata.setTableSchema(resultSet.getString("TABLE_SCHEMA"));
            columnMetadata.setTableName(resultSet.getString("TABLE_NAME"));
            columnMetadata.setColumnName(resultSet.getString("COLUMN_NAME"));
            columnMetadata.setOrdinalPosition(resultSet.getInt("ORDINAL_POSITION"));
            columnMetadata.setColumnDefault(resultSet.getString("COLUMN_DEFAULT"));
            columnMetadata.setIsNullable(resultSet.getBoolean("IS_NULLABLE"));
            columnMetadata.setDataType(resultSet.getString("DATA_TYPE"));
            columnMetadata.setCharacterMaximumLength(resultSet.getInt("CHARACTER_MAXIMUM_LENGTH"));
            columnMetadata.setCharacterOctetLength(resultSet.getInt("CHARACTER_OCTET_LENGTH"));
            columnMetadata.setNumericPrecision(resultSet.getInt("NUMERIC_PRECISION"));
            columnMetadata.setNumericScale(resultSet.getInt("NUMERIC_SCALE"));
            columnMetadata.setDatetimePrecision(resultSet.getInt("DATETIME_PRECISION"));
            columnMetadata.setCharacterSetName(resultSet.getString("CHARACTER_SET_NAME"));
            columnMetadata.setCollationName(resultSet.getString("COLLATION_NAME"));
            columnMetadata.setColumnType(resultSet.getString("COLUMN_TYPE"));
            columnMetadata.setColumnKey(resultSet.getString("COLUMN_KEY"));
            columnMetadata.setExtra(resultSet.getString("EXTRA"));
            columnMetadata.setPrivileges(resultSet.getString("PRIVILEGES"));
            columnMetadata.setColumnComment(resultSet.getString("COLUMN_COMMENT"));
            columnMetadataList.add(columnMetadata);
        }

        preparedStatement.close();
        resultSet.close();

        return columnMetadataList;
    }

    public List<ForeignKeyMetadata> getForeignKeyMetadata() throws SQLException {
        ResultSet resultSet;
        PreparedStatementGenerator fkMetadataPs = new PreparedStatementGenerator(
                """
                select cu.CONSTRAINT_NAME as 'ForeignKeyName',
                    cu.TABLE_SCHEMA as 'ReferencingTableSchema',
                    cu.TABLE_NAME as 'ReferencingTableName',
                    cu.COLUMN_NAME as 'ReferencingColumnName',
                    tc.TABLE_SCHEMA as 'ReferencedTableSchema',
                    tc.TABLE_NAME as 'ReferencedTableName',
                    tc.CONSTRAINT_TYPE,
                    rc.UNIQUE_CONSTRAINT_NAME as 'ReferencedKeyName'
                from INFORMATION_SCHEMA.KEY_COLUMN_USAGE cu
                    inner join INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS rc on rc.CONSTRAINT_NAME = cu.CONSTRAINT_NAME
                    inner join INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc on tc.CONSTRAINT_NAME = rc.UNIQUE_CONSTRAINT_NAME
                        and tc.TABLE_SCHEMA = rc.UNIQUE_CONSTRAINT_SCHEMA
                where cu.TABLE_SCHEMA = ?
                AND cu.TABLE_NAME NOT IN (""");
        fkMetadataPs.addValues(schemaName);
        fkMetadataPs.addVariables(ignoredTables.size());
        fkMetadataPs.addValues(ignoredTables.toArray());
        fkMetadataPs.appendSql(")\nAND tc.TABLE_NAME NOT IN (");
        fkMetadataPs.addVariables(ignoredTables.size());
        fkMetadataPs.addValues(ignoredTables.toArray());
        fkMetadataPs.appendSql(")\norder by cu.TABLE_SCHEMA, cu.TABLE_NAME, cu.CONSTRAINT_NAME, cu.COLUMN_NAME;");
        PreparedStatement preparedStatement = fkMetadataPs.generatePreparedStatement(connection);
        resultSet = preparedStatement.executeQuery();

        List<ForeignKeyMetadata> foreignKeyMetadataList = new ArrayList<>();
        while (resultSet.next()) {
            // Map to ForeignKeyMetadata
            ForeignKeyMetadata foreignKeyMetadata = new ForeignKeyMetadata();
            foreignKeyMetadata.setForeignKeyName(resultSet.getString("ForeignKeyName"));
            foreignKeyMetadata.setReferencingTableSchema(resultSet.getString("ReferencingTableSchema"));
            foreignKeyMetadata.setReferencingTableName(resultSet.getString("ReferencingTableName"));
            foreignKeyMetadata.setReferencingColumnName(resultSet.getString("ReferencingColumnName"));
            foreignKeyMetadata.setReferencedTableSchema(resultSet.getString("ReferencedTableSchema"));
            foreignKeyMetadata.setReferencedTableName(resultSet.getString("ReferencedTableName"));
            foreignKeyMetadata.setConstraintType(resultSet.getString("CONSTRAINT_TYPE"));
            foreignKeyMetadata.setReferencedKeyName(resultSet.getString("ReferencedKeyName"));
            foreignKeyMetadataList.add(foreignKeyMetadata);
        }

        preparedStatement.close();
        resultSet.close();

        return foreignKeyMetadataList;
    }
}
