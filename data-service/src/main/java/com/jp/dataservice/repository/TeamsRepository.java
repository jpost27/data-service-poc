package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeams;
import com.jp.dataservice.model.entity.QFdTeams;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TeamsRepository extends JPRepository<FdTeams, QFdTeams, Integer> {}
