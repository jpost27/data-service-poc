package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "competitor_event_statistics")
@ToString
@EqualsAndHashCode
@IdClass(FdCompetitorEventStatisticsId.class)
public class FdCompetitorEventStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competitors_events_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer competitorsEventsId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistic_type_code", nullable = false, unique = true)
    private String statisticTypeCode;

    @Column(name = "statistic_value", nullable = false)
    private BigDecimal statisticValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competitors_events_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdCompetitorsEvents fdCompetitorsEvents;

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

    public BigDecimal getStatisticValue() {
        return statisticValue;
    }

    public void setStatisticValue(BigDecimal statisticValue) {
        this.statisticValue = statisticValue;
    }

    public FdCompetitorsEvents getFdCompetitorsEvents() {
        return fdCompetitorsEvents;
    }

    public void setFdCompetitorsEvents(FdCompetitorsEvents fdCompetitorsEvents) {
        this.fdCompetitorsEvents = fdCompetitorsEvents;
    }
}
