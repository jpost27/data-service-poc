package com.jp.dataservice.model.dto;

import java.sql.Date;
import java.util.List;

public class Seasons {
    private Integer seasonId;

    private Date endDate;

    private Date startDate;

    private Integer year;

    private Integer leagueId;

    private String seasonTypeCode;

    private Leagues leagues;

    private SeasonTypes seasonTypes;

    private List<Events> events;

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public String getSeasonTypeCode() {
        return seasonTypeCode;
    }

    public void setSeasonTypeCode(String seasonTypeCode) {
        this.seasonTypeCode = seasonTypeCode;
    }

    public Leagues getLeagues() {
        return leagues;
    }

    public void setLeagues(Leagues leagues) {
        this.leagues = leagues;
    }

    public SeasonTypes getSeasonTypes() {
        return seasonTypes;
    }

    public void setSeasonTypes(SeasonTypes seasonTypes) {
        this.seasonTypes = seasonTypes;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }
}
