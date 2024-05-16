package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdTeamAssociations;
import com.jp.dataservice.model.entity.QFdTeamAssociations;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TeamAssociationsRepository extends JPRepository<FdTeamAssociations, QFdTeamAssociations, Integer> {}
