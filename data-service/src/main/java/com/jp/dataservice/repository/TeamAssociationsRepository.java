package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamAssociations;
import com.jp.dataservice.model.entity.QFdTeamAssociations;
import com.jp.dataservice.repository.base.JPRepository;
import java.util.Optional;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamAssociationsRepository extends JPRepository<FdTeamAssociations, QFdTeamAssociations, Integer> {

    Optional<FdTeamAssociations> findByName(String name);

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamAssociations root) {}
}
