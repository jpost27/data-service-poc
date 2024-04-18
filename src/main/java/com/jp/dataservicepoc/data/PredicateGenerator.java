package com.jp.dataservicepoc.data;

import com.jp.dataservicepoc.model.entity.QFdCompetitor;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import lombok.RequiredArgsConstructor;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@RequiredArgsConstructor
public class PredicateGenerator<E> {
    private final Class<E> entityClass;

    public BooleanExpression getPredicate(SearchCriteria criteria) {
        PathBuilder<E> entityPath = new PathBuilder<>(entityClass, entityClass.getSimpleName());

        if (isNumeric(criteria.getValue().toString())) {
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case ":":
                    return path.eq(value);
                case ">":
                    return path.goe(value);
                case "<":
                    return path.loe(value);
            }
        } else {
            StringPath path = entityPath.getString(criteria.getKey());
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }
        return null;
    }

    public BooleanExpression getPredicate(String key, String stringValue) {
        PathBuilder entityPath = new PathBuilder<>(entityClass,
                Character.toLowerCase(entityClass.getSimpleName().charAt(0)) + entityClass.getSimpleName().substring(1));

        if (isNumeric(stringValue)) {
            NumberPath<Integer> path = entityPath.getNumber(key, Integer.class);
            int intValue = Integer.parseInt(stringValue);
            return path.eq(intValue);
        } else {
            StringPath path = entityPath.getString(key);
            return path.containsIgnoreCase(stringValue);
        }
//        return null;
    }
}
