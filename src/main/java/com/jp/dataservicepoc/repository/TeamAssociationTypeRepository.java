package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.TeamAssociationType;
import com.jp.dataservicepoc.model.entity.FdTeamAssociationType;
import com.jp.dataservicepoc.model.entity.QFdTeamAssociationType;
import com.jp.dataservicepoc.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamAssociationTypeRepository extends JPRepository<TeamAssociationType, FdTeamAssociationType, QFdTeamAssociationType, String> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamAssociationType root) {

    }
}
