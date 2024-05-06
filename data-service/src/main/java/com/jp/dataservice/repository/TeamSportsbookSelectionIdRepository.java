package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamSportsbookSelectionId;
import com.jp.dataservice.model.entity.QFdTeamSportsbookSelectionId;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamSportsbookSelectionIdRepository
        extends JPRepository<FdTeamSportsbookSelectionId, QFdTeamSportsbookSelectionId, Long> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamSportsbookSelectionId root) {}
}
