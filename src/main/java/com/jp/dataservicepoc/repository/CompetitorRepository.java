package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.Competitor;
import com.jp.dataservicepoc.model.entity.FdCompetitor;
import com.jp.dataservicepoc.model.entity.QFdCompetitor;
import com.jp.dataservicepoc.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorRepository extends JPRepository<Competitor, FdCompetitor, QFdCompetitor, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdCompetitor root) {

    }
}
