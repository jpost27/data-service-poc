package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.TeamProviderId;
import com.jp.dataservicepoc.model.entity.FdTeamProviderId;
import com.jp.dataservicepoc.model.entity.QFdTeamProviderId;
import com.jp.dataservicepoc.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamProviderIdRepository extends JPRepository<TeamProviderId, FdTeamProviderId, QFdTeamProviderId, Integer> {

    Optional<FdTeamProviderId> findByFanduelTeamId(Integer fanduelTeamId);

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamProviderId root) {

    }

}
