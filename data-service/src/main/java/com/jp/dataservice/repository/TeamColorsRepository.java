package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamColors;
import com.jp.dataservice.model.entity.QFdTeamColors;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamColorsRepository extends JPRepository<FdTeamColors, QFdTeamColors, Integer> {
    @Override
    default void customize(QuerydslBindings bindings, QFdTeamColors root) {}
}
