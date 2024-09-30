package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdCompetitorSportsbookSelectionIds;
import com.jp.dataservice.model.entity.QFdCompetitorSportsbookSelectionIds;
import org.springframework.stereotype.Repository;

@Repository
interface CompetitorSportsbookSelectionIdsRepository
        extends JPRepository<FdCompetitorSportsbookSelectionIds, QFdCompetitorSportsbookSelectionIds, Long> {}
