package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdEventStatuses;
import com.jp.dataservice.model.entity.QFdEventStatuses;
import org.springframework.stereotype.Repository;

@Repository
interface EventStatusesRepository extends JPRepository<FdEventStatuses, QFdEventStatuses, String> {}
