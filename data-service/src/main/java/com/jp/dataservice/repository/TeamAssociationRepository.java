package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamAssociation;
import com.jp.dataservice.model.entity.QFdTeamAssociation;
import com.jp.dataservice.repository.base.JPRepository;
import java.util.Optional;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamAssociationRepository extends JPRepository<FdTeamAssociation, QFdTeamAssociation, Integer> {

    Optional<FdTeamAssociation> findByName(String name);

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamAssociation root) {}
}
