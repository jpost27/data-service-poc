package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdTeamAssociationTypes;
import com.jp.dataservice.model.entity.QFdTeamAssociationTypes;
import org.springframework.stereotype.Repository;

@Repository
interface TeamAssociationTypesRepository
        extends JPRepository<FdTeamAssociationTypes, QFdTeamAssociationTypes, String> {}
