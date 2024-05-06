package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdCompetitor;
import com.jp.dataservice.model.entity.QFdCompetitor;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorRepository extends JPRepository<FdCompetitor, QFdCompetitor, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdCompetitor root) {}
}
