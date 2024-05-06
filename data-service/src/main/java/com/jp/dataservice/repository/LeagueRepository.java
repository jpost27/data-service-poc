package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdLeague;
import com.jp.dataservice.model.entity.QFdLeague;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JPRepository<FdLeague, QFdLeague, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdLeague root) {
        //        bindings.bind(String.class)
        //                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        //        bindings.excluding(root.email);
    }
}
