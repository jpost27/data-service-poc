package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdEvents;
import com.jp.dataservice.model.entity.QFdEvents;
import org.springframework.stereotype.Repository;

@Repository
interface EventsRepository extends JPRepository<FdEvents, QFdEvents, Integer> {}
