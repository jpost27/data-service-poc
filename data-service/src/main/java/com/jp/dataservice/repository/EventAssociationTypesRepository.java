package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdEventAssociationTypes;
import com.jp.dataservice.model.entity.QFdEventAssociationTypes;
import org.springframework.stereotype.Repository;

@Repository
interface EventAssociationTypesRepository
        extends JPRepository<FdEventAssociationTypes, QFdEventAssociationTypes, String> {}
