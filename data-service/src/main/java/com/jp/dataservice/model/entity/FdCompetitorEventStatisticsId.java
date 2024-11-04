package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class FdCompetitorEventStatisticsId {
    @Column(name = "competitors_events_id")
    private Integer competitorsEventsId;

    @Column(name = "statistic_type_code")
    private String statisticTypeCode;

    public Integer getCompetitorsEventsId() {
        return competitorsEventsId;
    }

    public void setCompetitorsEventsId(Integer competitorsEventsId) {
        this.competitorsEventsId = competitorsEventsId;
    }

    public String getStatisticTypeCode() {
        return statisticTypeCode;
    }

    public void setStatisticTypeCode(String statisticTypeCode) {
        this.statisticTypeCode = statisticTypeCode;
    }
}
