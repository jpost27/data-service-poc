package com.jp.codegen.model.enums;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ColumnTypes {
    VARCHAR(String.class, Set.of("VARCHAR", "CHAR", "TEXT")),
    INTEGER(Integer.class, Set.of("INT")),
    BIG_INT(BigInteger.class, Set.of("BIGINT")),
    BIG_DECIMAL(BigDecimal.class, Set.of("DECIMAL")),
    BOOLEAN(Boolean.class, Set.of("BOOLEAN")),
    DATE(LocalDate.class, Set.of("DATE")),
    DATE_TIME(LocalDateTime.class, Set.of("DATETIME", "TIMESTAMP")),
    ;

    private final Class<?> javaType;
    private final Set<String> sqlTypes;

    public static ColumnTypes fromSqlType(@NonNull String sqlType) {
        sqlType = sqlType.toUpperCase();
        for (ColumnTypes columnType : ColumnTypes.values()) {
            if (columnType.sqlTypes.contains(sqlType)) {
                return columnType;
            }
        }
        throw new IllegalArgumentException("Unknown SQL type: " + sqlType);
    }
}
