package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdCompetitorsEvents;
import com.jp.dataservice.model.entity.FdCompetitorsEventsId;
import com.jp.dataservice.model.entity.QFdCompetitorsEvents;
import org.springframework.stereotype.Repository;

@Repository
interface CompetitorsEventsRepository
        extends JPRepository<FdCompetitorsEvents, QFdCompetitorsEvents, FdCompetitorsEventsId> {}
