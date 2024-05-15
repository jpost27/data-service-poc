package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamSportsbookSelectionIds;
import com.jp.dataservice.model.entity.QFdTeamSportsbookSelectionIds;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamSportsbookSelectionIdsRepository
        extends JPRepository<FdTeamSportsbookSelectionIds, QFdTeamSportsbookSelectionIds, Long> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamSportsbookSelectionIds root) {}
}
