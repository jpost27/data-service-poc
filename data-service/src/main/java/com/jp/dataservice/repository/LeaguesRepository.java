package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdLeagues;
import com.jp.dataservice.model.entity.QFdLeagues;
import org.springframework.stereotype.Repository;

@Repository
interface LeaguesRepository extends JPRepository<FdLeagues, QFdLeagues, Integer> {}
