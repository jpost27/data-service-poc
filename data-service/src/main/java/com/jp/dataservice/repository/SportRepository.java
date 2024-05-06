package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdSport;
import com.jp.dataservice.model.entity.QFdSport;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JPRepository<FdSport, QFdSport, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdSport root) {}
}
