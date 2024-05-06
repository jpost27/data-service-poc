package com.jp.dataservice.repository;

import com.jp.dataservice.model.entity.FdVenue;
import com.jp.dataservice.model.entity.QFdVenue;
import com.jp.dataservice.repository.base.JPRepository;
import java.math.BigDecimal;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JPRepository<FdVenue, QFdVenue, Integer> {

    FdVenue findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

    @Override
    default void customize(QuerydslBindings bindings, QFdVenue root) {}
}
