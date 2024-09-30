package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdSports;
import com.jp.dataservice.model.entity.QFdSports;
import org.springframework.stereotype.Repository;

@Repository
interface SportsRepository extends JPRepository<FdSports, QFdSports, Integer> {}
