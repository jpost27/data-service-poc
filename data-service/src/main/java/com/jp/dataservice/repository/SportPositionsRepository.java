package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdSportPositions;
import com.jp.dataservice.model.entity.FdSportPositionsId;
import com.jp.dataservice.model.entity.QFdSportPositions;
import org.springframework.stereotype.Repository;

@Repository
interface SportPositionsRepository extends JPRepository<FdSportPositions, QFdSportPositions, FdSportPositionsId> {}
