package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdEventProviderIds;
import com.jp.dataservice.model.entity.QFdEventProviderIds;
import org.springframework.stereotype.Repository;

@Repository
interface EventProviderIdsRepository extends JPRepository<FdEventProviderIds, QFdEventProviderIds, Integer> {}
