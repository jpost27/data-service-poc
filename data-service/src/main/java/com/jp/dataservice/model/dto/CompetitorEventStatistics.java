package com.jp.dataservice.model.dto;

import java.math.BigDecimal;

public class CompetitorEventStatistics {
    private Integer competitorsEventsId;

    private String statisticTypeCode;

    private BigDecimal statisticValue;

    private CompetitorsEvents competitorsEvents;

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

    public CompetitorsEvents getCompetitorsEvents() {
        return competitorsEvents;
    }

    public void setCompetitorsEvents(CompetitorsEvents competitorsEvents) {
        this.competitorsEvents = competitorsEvents;
    }
}
