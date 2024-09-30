package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdTeamProviderIds;
import com.jp.dataservice.model.entity.QFdTeamProviderIds;
import org.springframework.stereotype.Repository;

@Repository
interface TeamProviderIdsRepository extends JPRepository<FdTeamProviderIds, QFdTeamProviderIds, Integer> {}
