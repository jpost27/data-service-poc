package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdSports;
import com.jp.dataservice.model.entity.QFdSports;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsRepository extends JPRepository<FdSports, QFdSports, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdSports root) {}
}
