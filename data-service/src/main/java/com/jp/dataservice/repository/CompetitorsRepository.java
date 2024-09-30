package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdCompetitors;
import com.jp.dataservice.model.entity.QFdCompetitors;
import org.springframework.stereotype.Repository;

@Repository
interface CompetitorsRepository extends JPRepository<FdCompetitors, QFdCompetitors, Integer> {}
