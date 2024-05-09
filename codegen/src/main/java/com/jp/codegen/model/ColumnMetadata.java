package com.jp.codegen.model;

import lombok.Data;

/**
 * TABLE_CATALOG	TABLE_SCHEMA	TABLE_NAME	            COLUMN_NAME	        ORDINAL_POSITION	COLUMN_DEFAULT	IS_NULLABLE	DATA_TYPE	CHARACTER_MAXIMUM_LENGTH	CHARACTER_OCTET_LENGTH	NUMERIC_PRECISION	NUMERIC_SCALE	DATETIME_PRECISION	CHARACTER_SET_NAME	COLLATION_NAME	COLUMN_TYPE	COLUMN_KEY	EXTRA	PRIVILEGES	                        COLUMN_COMMENT	GENERATION_EXPRESSION	SRS_ID
 * def              sport_data	    BATCH_JOB_EXECUTION	    JOB_EXECUTION_ID	1		            NULL            NO	        bigint			                        				        19                  0                                                                        bigint      PRI		        select,insert,update,references
 */
@Data
public class ColumnMetadata {

    private String tableCatalog; // TABLE_CATALOG
    private String tableSchema; // TABLE_SCHEMA
    private String tableName; // TABLE_NAME
    private String columnName; // COLUMN_NAME
    private Integer ordinalPosition; // ORDINAL_POSITION
    private String columnDefault; // COLUMN_DEFAULT
    private Boolean isNullable; // IS_NULLABLE
    private String dataType; // DATA_TYPE
    private Integer characterMaximumLength; // CHARACTER_MAXIMUM_LENGTH
    private Integer characterOctetLength; // CHARACTER_OCTET_LENGTH
    private Integer numericPrecision; // NUMERIC_PRECISION
    private Integer numericScale; // NUMERIC_SCALE
    private Integer datetimePrecision; // DATETIME_PRECISION
    private String characterSetName; // CHARACTER_SET_NAME
    private String collationName; // COLLATION_NAME
    private String columnType; // COLUMN_TYPE
    private String columnKey; // COLUMN_KEY
    private String extra; // EXTRA
    private String privileges; // PRIVILEGES
    private String columnComment; // COLUMN_COMMENT
}
