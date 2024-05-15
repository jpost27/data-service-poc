package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdLeagues;
import com.jp.dataservice.model.entity.QFdLeagues;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaguesRepository extends JPRepository<FdLeagues, QFdLeagues, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdLeagues root) {
        //        bindings.bind(String.class)
        //                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        //        bindings.excluding(root.email);
    }
}
