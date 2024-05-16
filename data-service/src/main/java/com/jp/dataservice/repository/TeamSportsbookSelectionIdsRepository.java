package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamSportsbookSelectionIds;
import com.jp.dataservice.model.entity.QFdTeamSportsbookSelectionIds;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TeamSportsbookSelectionIdsRepository
        extends JPRepository<FdTeamSportsbookSelectionIds, QFdTeamSportsbookSelectionIds, Long> {}
