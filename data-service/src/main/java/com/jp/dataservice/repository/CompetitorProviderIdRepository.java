package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdCompetitorProviderId;
import com.jp.dataservice.model.entity.QFdCompetitorProviderId;
import com.jp.dataservice.repository.base.JPRepository;
import java.util.Optional;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorProviderIdRepository
        extends JPRepository<FdCompetitorProviderId, QFdCompetitorProviderId, Integer> {

    Optional<FdCompetitorProviderId> findBySportradarCompetitorId(String sportradarCompetitorId);

    @Override
    default void customize(QuerydslBindings bindings, QFdCompetitorProviderId root) {}
}
