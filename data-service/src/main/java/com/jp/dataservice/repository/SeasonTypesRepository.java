package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdSeasonTypes;
import com.jp.dataservice.model.entity.QFdSeasonTypes;
import org.springframework.stereotype.Repository;

@Repository
interface SeasonTypesRepository extends JPRepository<FdSeasonTypes, QFdSeasonTypes, String> {}
