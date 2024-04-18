package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.TeamColor;
import com.jp.dataservicepoc.model.entity.FdTeamColor;
import com.jp.dataservicepoc.model.entity.QFdTeamColor;
import com.jp.dataservicepoc.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamColorRepository extends JPRepository<TeamColor, FdTeamColor, QFdTeamColor, Integer> {
    @Override
    default void customize(QuerydslBindings bindings, QFdTeamColor root) {

    }
}
