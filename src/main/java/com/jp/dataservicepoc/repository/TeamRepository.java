package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.Team;
import com.jp.dataservicepoc.model.entity.FdTeam;
import com.jp.dataservicepoc.model.entity.QFdTeam;
import com.jp.dataservicepoc.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JPRepository<Team, FdTeam, QFdTeam, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeam root) {}
}
