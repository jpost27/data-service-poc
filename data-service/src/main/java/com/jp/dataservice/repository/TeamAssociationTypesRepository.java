package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamAssociationTypes;
import com.jp.dataservice.model.entity.QFdTeamAssociationTypes;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamAssociationTypesRepository
        extends JPRepository<FdTeamAssociationTypes, QFdTeamAssociationTypes, String> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamAssociationTypes root) {}
}
