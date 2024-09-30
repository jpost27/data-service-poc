package com.jp.dataservice.repository;

import com.jp.dataservice.framework.JPRepository;
import com.jp.dataservice.model.entity.FdTeamAssociations;
import com.jp.dataservice.model.entity.QFdTeamAssociations;
import org.springframework.stereotype.Repository;

@Repository
interface TeamAssociationsRepository extends JPRepository<FdTeamAssociations, QFdTeamAssociations, Integer> {}
