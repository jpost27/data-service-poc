package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamProviderId;
import com.jp.dataservice.model.entity.QFdTeamProviderId;
import com.jp.dataservice.repository.base.JPRepository;
import java.util.Optional;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamProviderIdRepository extends JPRepository<FdTeamProviderId, QFdTeamProviderId, Integer> {

    Optional<FdTeamProviderId> findByFanduelTeamId(Integer fanduelTeamId);

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamProviderId root) {}
}
