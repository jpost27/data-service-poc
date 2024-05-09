package com.jp.codegen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class PreparedStatementGenerator {

    private final StringBuilder preparedStatement;
    private final Queue<Object> values;

    public PreparedStatementGenerator() {
        this.preparedStatement = new StringBuilder();
        values = new LinkedList<>();
    }

    public PreparedStatementGenerator(String initialStatement) {
        this.preparedStatement = new StringBuilder(initialStatement);
        values = new LinkedList<>();
    }

    public PreparedStatementGenerator(StringBuilder initialStatement) {
        this.preparedStatement = new StringBuilder(initialStatement);
        values = new LinkedList<>();
    }

    public PreparedStatementGenerator addVariables(int numberOfVariables) {
        for (int i = 0; i < numberOfVariables; i++) {
            preparedStatement.append("?");
            if (i < numberOfVariables - 1) {
                preparedStatement.append(", ");
            }
        }
        return this;
    }

    public PreparedStatementGenerator addValues(Object... values) {
        this.values.addAll(Arrays.asList(values));
        return this;
    }

    public PreparedStatementGenerator appendSql(String statement) {
        preparedStatement.append(statement);
        return this;
    }

    public PreparedStatement generatePreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(preparedStatement.toString());
        AtomicInteger index = new AtomicInteger(1);
        for (Object value : values) {
            if (value instanceof String) {
                ps.setString(index.getAndIncrement(), (String) value);
            } else if (value instanceof Integer) {
                ps.setInt(index.getAndIncrement(), (Integer) value);
            } else if (value instanceof Boolean) {
                ps.setBoolean(index.getAndIncrement(), (Boolean) value);
            } else {
                throw new IllegalArgumentException("Unsupported type: " + value.getClass());
            }
        }
        return ps;
    }
}
