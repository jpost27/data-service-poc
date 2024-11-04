package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdEventAssociations;
import com.jp.dataservice.model.entity.QFdEventAssociations;
import org.springframework.stereotype.Repository;

@Repository
interface EventAssociationsRepository extends JPRepository<FdEventAssociations, QFdEventAssociations, Integer> {}
