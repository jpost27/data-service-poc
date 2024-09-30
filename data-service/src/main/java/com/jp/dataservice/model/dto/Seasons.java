package com.jp.dataservice.model.dto;

import java.sql.Date;

public class Seasons {
    private Integer leagueId;

    private String seasonTypeCode;

    private Integer year;

    private Date endDate;

    private Date startDate;

    private Leagues leagues;

    private SeasonTypes seasonTypes;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
}
