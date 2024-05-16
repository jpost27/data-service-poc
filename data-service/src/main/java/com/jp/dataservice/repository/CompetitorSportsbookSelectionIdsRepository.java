package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdCompetitorSportsbookSelectionIds;
import com.jp.dataservice.model.entity.QFdCompetitorSportsbookSelectionIds;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CompetitorSportsbookSelectionIdsRepository
        extends JPRepository<FdCompetitorSportsbookSelectionIds, QFdCompetitorSportsbookSelectionIds, Long> {}
