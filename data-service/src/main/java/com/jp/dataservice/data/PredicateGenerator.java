package com.jp.dataservice.data;

import static org.apache.commons.lang3.StringUtils.isNumeric;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import lombok.RequiredArgsConstructor;

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

        String[] stringPaths = key.split("\\.");

        PathBuilder<Object> entityPath = new PathBuilder<>(
                entityClass,
                Character.toLowerCase(entityClass.getSimpleName().charAt(0))
                        + entityClass.getSimpleName().substring(1));

        for (int index = 0; index < stringPaths.length - 1; index++) {
            entityPath = entityPath.get(PersistenceMapping.queryStringToGraphPath(stringPaths[index]));
        }

        StringPath path = entityPath.getString(stringPaths[stringPaths.length - 1]);
        return path.eq(stringValue);
    }
}
