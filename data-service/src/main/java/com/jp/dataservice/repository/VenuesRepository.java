package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdVenues;
import com.jp.dataservice.model.entity.QFdVenues;
import com.jp.dataservice.repository.base.JPRepository;
import org.springframework.stereotype.Repository;

@Repository
interface VenuesRepository extends JPRepository<FdVenues, QFdVenues, Integer> {}
