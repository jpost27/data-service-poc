package com.jp.dataservice.framework;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import java.util.List;
import java.util.Objects;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ParameterPredicateBuilder<E> {
    private final MultiValueMap<String, String> params;
    private final Class<E> entityClass;

    public ParameterPredicateBuilder(Class<E> entityClass) {
        params = new LinkedMultiValueMap<>();
        this.entityClass = entityClass;
    }

    public ParameterPredicateBuilder(Class<E> entityClass, MultiValueMap<String, String> params) {
        this.params = new LinkedMultiValueMap<>(params);
        this.entityClass = entityClass;
    }

    public ParameterPredicateBuilder<E> with(String key, String value) {
        params.add(key, value);
        return this;
    }

    public BooleanExpression build() {
        if (params.size() == 0) {
            return null;
        }

        PredicateGenerator<E> predicateGenerator = new PredicateGenerator<>(entityClass);
        List<BooleanExpression> predicates = params.entrySet().stream()
                .flatMap(entry ->
                        entry.getValue().stream().map(value -> predicateGenerator.getPredicate(entry.getKey(), value)))
                .filter(Objects::nonNull)
                .toList();

        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }
        return result;
    }
}
