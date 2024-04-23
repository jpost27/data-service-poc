package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.TeamSportsbookSelectionId;
import com.jp.dataservicepoc.model.entity.FdTeamSportsbookSelectionId;
import com.jp.dataservicepoc.model.entity.QFdTeamSportsbookSelectionId;
import com.jp.dataservicepoc.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamSportsbookSelectionIdRepository
        extends JPRepository<FdTeamSportsbookSelectionId, QFdTeamSportsbookSelectionId, Long> {

    @Override
    default void customize(QuerydslBindings bindings, QFdTeamSportsbookSelectionId root) {}
}
