package com.jp.dataservice.data;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchPredicateBuilder<E> {
    private final List<SearchCriteria> params;
    private final Class<E> entityClass;

    public SearchPredicateBuilder(Class<E> entityClass) {
        params = new ArrayList<>();
        this.entityClass = entityClass;
    }

    public SearchPredicateBuilder<E> with(String key, String operation, Object value) {

        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.size() == 0) {
            return null;
        }

        List<BooleanExpression> predicates = params.stream()
                .map(param -> {
                    PredicateGenerator<E> predicate = new PredicateGenerator<>(entityClass);
                    return predicate.getPredicate(param);
                })
                .filter(Objects::nonNull)
                .toList();

        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }
        return result;
    }
}
