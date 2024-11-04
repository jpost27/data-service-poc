package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class FdCompetitorsEventsId {
    @Column(name = "competitor_id")
    private Integer competitorId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "team_id")
    private Integer teamId;

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
