package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdCompetitorEventStatistics;
import com.jp.dataservice.model.entity.FdCompetitorEventStatisticsId;
import com.jp.dataservice.model.entity.QFdCompetitorEventStatistics;
import org.springframework.stereotype.Repository;

@Repository
interface CompetitorEventStatisticsRepository
        extends JPRepository<
                FdCompetitorEventStatistics, QFdCompetitorEventStatistics, FdCompetitorEventStatisticsId> {}
