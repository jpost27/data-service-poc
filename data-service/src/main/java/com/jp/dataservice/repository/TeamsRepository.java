package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeams;
import com.jp.dataservice.model.entity.QFdTeams;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends JPRepository<FdTeams, QFdTeams, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeams root) {}
}
