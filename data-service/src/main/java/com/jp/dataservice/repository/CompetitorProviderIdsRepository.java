package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdCompetitorProviderIds;
import com.jp.dataservice.model.entity.QFdCompetitorProviderIds;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CompetitorProviderIdsRepository
        extends JPRepository<FdCompetitorProviderIds, QFdCompetitorProviderIds, Integer> {}
