package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.Sport;
import com.jp.dataservicepoc.model.entity.FdSport;
import com.jp.dataservicepoc.model.entity.QFdSport;
import com.jp.dataservicepoc.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JPRepository<Sport, FdSport, QFdSport, Integer> {

    @Override
    default void customize(QuerydslBindings bindings, QFdSport root) {}
}
