package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdInjuryStatuses;
import com.jp.dataservice.model.entity.QFdInjuryStatuses;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.stereotype.Repository;

@Repository
interface InjuryStatusesRepository extends JPRepository<FdInjuryStatuses, QFdInjuryStatuses, String> {}
