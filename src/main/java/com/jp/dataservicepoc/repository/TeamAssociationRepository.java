package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.TeamAssociation;
import com.jp.dataservicepoc.model.entity.FdTeamAssociation;
import com.jp.dataservicepoc.model.entity.QFdTeamAssociation;
import com.jp.dataservicepoc.repository.base.JPRepository;
import java.util.Optional;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamAssociationRepository
        extends JPRepository<TeamAssociation, FdTeamAssociation, QFdTeamAssociation, Integer> {

    Optional<FdTeamAssociation> findByName(String name);

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamAssociation root) {}
}
