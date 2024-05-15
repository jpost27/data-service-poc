package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamColorTypes;
import com.jp.dataservice.model.entity.QFdTeamColorTypes;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamColorTypesRepository extends JPRepository<FdTeamColorTypes, QFdTeamColorTypes, String> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamColorTypes root) {}
}
