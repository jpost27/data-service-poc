package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdCompetitorStatuses;
import com.jp.dataservice.model.entity.QFdCompetitorStatuses;
import org.springframework.stereotype.Repository;

@Repository
interface CompetitorStatusesRepository extends JPRepository<FdCompetitorStatuses, QFdCompetitorStatuses, String> {}
