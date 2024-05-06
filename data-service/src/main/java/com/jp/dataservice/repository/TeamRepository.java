package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeam;
import com.jp.dataservice.model.entity.QFdTeam;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JPRepository<FdTeam, QFdTeam, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeam root) {}
}
