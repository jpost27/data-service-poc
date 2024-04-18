package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.TeamColorType;
import com.jp.dataservicepoc.model.entity.FdTeamColorType;
import com.jp.dataservicepoc.model.entity.QFdTeamColorType;
import com.jp.dataservicepoc.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamColorTypeRepository extends JPRepository<TeamColorType, FdTeamColorType, QFdTeamColorType, String> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamColorType root) {

    }
}
