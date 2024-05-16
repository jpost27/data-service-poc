package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamColorTypes;
import com.jp.dataservice.model.entity.QFdTeamColorTypes;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TeamColorTypesRepository extends JPRepository<FdTeamColorTypes, QFdTeamColorTypes, String> {}
