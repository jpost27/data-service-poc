package com.jp.codegen.model;

import lombok.Data;

/**
 * ForeignKeyName	            ReferencingTableSchema	ReferencingTableName	ReferencingColumnName	ReferencedTableSchema	ReferencedTableName	    CONSTRAINT_TYPE	ReferencedKeyName
 * FKmyi2mb6c5y19d45y0v51k74ub	sport_data	            competitor_provider_ids	competitor_id	        sport_data	            competitor_provider_ids	PRIMARY KEY	    PRIMARY
 */
@Data
public class ForeignKeyMetadata {

    private String foreignKeyName;
    private String referencingTableSchema;
    private String referencingTableName;
    private String referencingColumnName;
    private String referencedTableSchema;
    private String referencedTableName;
    private String constraintType;
    private String referencedKeyName;

    //    select cu.CONSTRAINT_NAME as 'ForeignKeyName',
    //    cu.TABLE_SCHEMA as 'ReferencingTableSchema',
    //    cu.TABLE_NAME as 'ReferencingTableName',
    //    cu.COLUMN_NAME as 'ReferencingColumnName',
    //    tc.TABLE_SCHEMA as 'ReferencedTableSchema',
    //    tc.TABLE_NAME as 'ReferencedTableName',
    //    tc.CONSTRAINT_TYPE,
    //    rc.UNIQUE_CONSTRAINT_NAME as 'ReferencedKeyName'
}
