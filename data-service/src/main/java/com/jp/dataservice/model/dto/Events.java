package com.jp.dataservice.model.dto;

import java.sql.Timestamp;
import java.util.List;

public class Events {
    private Integer eventId;

    private Integer awayTeamId;

    private Integer awayTeamScore;

    private Integer homeTeamId;

    private Integer homeTeamScore;

    private Integer seasonId;

    private Integer sequence;

    private Integer venueId;

    private Timestamp scheduledTime;

    private String eventStatusCode;

    private String eventTitle;

    private Seasons seasons;

    private Teams homeTeams;

    private Teams awayTeams;

    private EventStatuses eventStatuses;

    private Venues venues;

    private EventProviderIds eventProviderIds;

    private List<EventAssociations> eventAssociations;

    private List<CompetitorsEvents> competitorsEvents;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(Integer awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Timestamp getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Timestamp scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getEventStatusCode() {
        return eventStatusCode;
    }

    public void setEventStatusCode(String eventStatusCode) {
        this.eventStatusCode = eventStatusCode;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Seasons getSeasons() {
        return seasons;
    }

    public void setSeasons(Seasons seasons) {
        this.seasons = seasons;
    }

    public Teams getHomeTeams() {
        return homeTeams;
    }

    public void setHomeTeams(Teams homeTeams) {
        this.homeTeams = homeTeams;
    }

    public Teams getAwayTeams() {
        return awayTeams;
    }

    public void setAwayTeams(Teams awayTeams) {
        this.awayTeams = awayTeams;
    }

    public EventStatuses getEventStatuses() {
        return eventStatuses;
    }

    public void setEventStatuses(EventStatuses eventStatuses) {
        this.eventStatuses = eventStatuses;
    }

    public Venues getVenues() {
        return venues;
    }

    public void setVenues(Venues venues) {
        this.venues = venues;
    }

    public EventProviderIds getEventProviderIds() {
        return eventProviderIds;
    }

    public void setEventProviderIds(EventProviderIds eventProviderIds) {
        this.eventProviderIds = eventProviderIds;
    }

    public List<EventAssociations> getEventAssociations() {
        return eventAssociations;
    }

    public void setEventAssociations(List<EventAssociations> eventAssociations) {
        this.eventAssociations = eventAssociations;
    }

    public List<CompetitorsEvents> getCompetitorsEvents() {
        return competitorsEvents;
    }

    public void setCompetitorsEvents(List<CompetitorsEvents> competitorsEvents) {
        this.competitorsEvents = competitorsEvents;
    }
}
