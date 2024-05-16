package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdLeagues;
import com.jp.dataservice.model.entity.QFdLeagues;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LeaguesRepository extends JPRepository<FdLeagues, QFdLeagues, Integer> {}
