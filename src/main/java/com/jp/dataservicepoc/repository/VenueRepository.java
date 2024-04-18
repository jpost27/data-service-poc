package com.jp.dataservicepoc.repository;

import com.jp.dataservicepoc.model.dto.Venue;
import com.jp.dataservicepoc.model.entity.FdVenue;
import com.jp.dataservicepoc.model.entity.QFdVenue;
import com.jp.dataservicepoc.repository.base.JPRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface VenueRepository extends JPRepository<Venue, FdVenue, QFdVenue, Integer> {

    FdVenue findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

    @Override
    default void customize(QuerydslBindings bindings, QFdVenue root) {

    }
}
