package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdVenues;
import com.jp.dataservice.model.entity.QFdVenues;
import com.jp.dataservice.repository.base.JPRepository;
import java.math.BigDecimal;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface VenuesRepository extends JPRepository<FdVenues, QFdVenues, Integer> {

    FdVenues findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

    @Override
    default void customize(QuerydslBindings bindings, QFdVenues root) {}
}
