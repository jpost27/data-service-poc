package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamColor;
import com.jp.dataservice.model.entity.QFdTeamColor;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamColorRepository extends JPRepository<FdTeamColor, QFdTeamColor, Integer> {
    @Override
    default void customize(QuerydslBindings bindings, QFdTeamColor root) {}
}
