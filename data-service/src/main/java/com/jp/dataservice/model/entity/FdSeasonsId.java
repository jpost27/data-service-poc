package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class FdSeasonsId {
    @Column(name = "league_id")
    private Integer leagueId;

    @Column(name = "season_type_code")
    private String seasonTypeCode;

    @Column(name = "year")
    private Integer year;

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
}
