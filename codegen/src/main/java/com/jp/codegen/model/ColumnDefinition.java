package com.jp.codegen.model;

import com.jp.codegen.model.enums.ColumnTypes;

public record ColumnDefinition(
        Integer ordinalPosition,
        String name,
        ColumnTypes type,
        boolean nullable,
        Integer maxLength,
        boolean unique,
        boolean primaryKey,
        boolean foreignKey,
        String foreignKeyTable) {}
