package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamAssociationType;
import com.jp.dataservice.model.entity.QFdTeamAssociationType;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamAssociationTypeRepository
        extends JPRepository<FdTeamAssociationType, QFdTeamAssociationType, String> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamAssociationType root) {}
}
