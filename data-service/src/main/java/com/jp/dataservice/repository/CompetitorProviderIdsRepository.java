package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdCompetitorProviderIds;
import com.jp.dataservice.model.entity.QFdCompetitorProviderIds;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorProviderIdsRepository
        extends JPRepository<FdCompetitorProviderIds, QFdCompetitorProviderIds, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdCompetitorProviderIds root) {}
}
