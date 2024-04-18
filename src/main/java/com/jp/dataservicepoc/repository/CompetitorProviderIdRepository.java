package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.CompetitorProviderId;
import com.jp.dataservicepoc.model.entity.FdCompetitorProviderId;
import com.jp.dataservicepoc.model.entity.QFdCompetitorProviderId;
import com.jp.dataservicepoc.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetitorProviderIdRepository extends JPRepository<CompetitorProviderId, FdCompetitorProviderId, QFdCompetitorProviderId, Integer> {

    Optional<FdCompetitorProviderId> findBySportradarCompetitorId(String sportradarCompetitorId);

    @Override
    default void customize(QuerydslBindings bindings, QFdCompetitorProviderId root) {}

}
