package com.jp.dataservice.model.dto;

import java.util.List;

public class CompetitorsEvents {
    private Integer competitorsEventsId;

    private Integer competitorId;

    private Integer eventId;

    private Integer teamId;

    private Competitors competitors;

    private Events events;

    private Teams teams;

    private List<CompetitorEventStatistics> competitorEventStatistics;

    public Integer getCompetitorsEventsId() {
        return competitorsEventsId;
    }

    public void setCompetitorsEventsId(Integer competitorsEventsId) {
        this.competitorsEventsId = competitorsEventsId;
    }

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

    public Competitors getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Competitors competitors) {
        this.competitors = competitors;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public List<CompetitorEventStatistics> getCompetitorEventStatistics() {
        return competitorEventStatistics;
    }

    public void setCompetitorEventStatistics(List<CompetitorEventStatistics> competitorEventStatistics) {
        this.competitorEventStatistics = competitorEventStatistics;
    }
}
