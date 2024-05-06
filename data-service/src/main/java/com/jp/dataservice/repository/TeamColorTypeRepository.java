package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamColorType;
import com.jp.dataservice.model.entity.QFdTeamColorType;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamColorTypeRepository extends JPRepository<FdTeamColorType, QFdTeamColorType, String> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamColorType root) {}
}
