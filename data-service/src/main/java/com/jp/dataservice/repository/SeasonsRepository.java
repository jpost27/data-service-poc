package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdSeasons;
import com.jp.dataservice.model.entity.FdSeasonsId;
import com.jp.dataservice.model.entity.QFdSeasons;
import org.springframework.stereotype.Repository;

@Repository
interface SeasonsRepository extends JPRepository<FdSeasons, QFdSeasons, FdSeasonsId> {}
