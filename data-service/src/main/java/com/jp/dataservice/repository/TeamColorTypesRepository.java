package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdTeamColorTypes;
import com.jp.dataservice.model.entity.QFdTeamColorTypes;
import org.springframework.stereotype.Repository;

@Repository
interface TeamColorTypesRepository extends JPRepository<FdTeamColorTypes, QFdTeamColorTypes, String> {}
