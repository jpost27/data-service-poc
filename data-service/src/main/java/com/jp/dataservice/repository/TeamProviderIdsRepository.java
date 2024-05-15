package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamProviderIds;
import com.jp.dataservice.model.entity.QFdTeamProviderIds;
import com.jp.dataservice.repository.base.JPRepository;
import java.util.Optional;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamProviderIdsRepository extends JPRepository<FdTeamProviderIds, QFdTeamProviderIds, Integer> {

    Optional<FdTeamProviderIds> findByFanduelTeamId(Integer fanduelTeamId);

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamProviderIds root) {}
}
