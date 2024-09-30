package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdCompetitorProviderIds;
import com.jp.dataservice.model.entity.QFdCompetitorProviderIds;
import org.springframework.stereotype.Repository;

@Repository
interface CompetitorProviderIdsRepository
        extends JPRepository<FdCompetitorProviderIds, QFdCompetitorProviderIds, Integer> {}
