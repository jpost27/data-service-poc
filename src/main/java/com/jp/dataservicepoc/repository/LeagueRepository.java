package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.League;
import com.jp.dataservicepoc.model.entity.FdLeague;
import com.jp.dataservicepoc.model.entity.QFdLeague;
import com.jp.dataservicepoc.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JPRepository<League, FdLeague, QFdLeague, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdLeague root) {
        //        bindings.bind(String.class)
        //                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        //        bindings.excluding(root.email);
    }
}
