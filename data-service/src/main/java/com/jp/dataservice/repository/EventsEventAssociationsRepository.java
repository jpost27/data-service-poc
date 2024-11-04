package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdEventsEventAssociations;
import com.jp.dataservice.model.entity.QFdEventsEventAssociations;
import org.springframework.stereotype.Repository;

@Repository
interface EventsEventAssociationsRepository
        extends JPRepository<FdEventsEventAssociations, QFdEventsEventAssociations, Object> {}
