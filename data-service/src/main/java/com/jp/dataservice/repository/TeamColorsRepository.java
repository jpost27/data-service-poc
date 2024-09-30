package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdTeamColors;
import com.jp.dataservice.model.entity.QFdTeamColors;
import org.springframework.stereotype.Repository;

@Repository
interface TeamColorsRepository extends JPRepository<FdTeamColors, QFdTeamColors, Integer> {}
