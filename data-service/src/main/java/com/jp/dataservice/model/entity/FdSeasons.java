package com.jp.dataservice.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import java.sql.Date;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "seasons")
@ToString
@EqualsAndHashCode
@IdClass(FdSeasonsId.class)
public class FdSeasons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer leagueId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_type_code", nullable = false, unique = true, insertable = false, updatable = false)
    private String seasonTypeCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year", nullable = false, unique = true)
    private Integer year;

    @Column(name = "end_date")
    @Nullable
    private Date endDate;

    @Column(name = "start_date")
    @Nullable
    private Date startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "league_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdLeagues fdLeagues;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "season_type_code")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdSeasonTypes fdSeasonTypes;

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

    public FdLeagues getFdLeagues() {
        return fdLeagues;
    }

    public void setFdLeagues(FdLeagues fdLeagues) {
        this.fdLeagues = fdLeagues;
    }

    public FdSeasonTypes getFdSeasonTypes() {
        return fdSeasonTypes;
    }

    public void setFdSeasonTypes(FdSeasonTypes fdSeasonTypes) {
        this.fdSeasonTypes = fdSeasonTypes;
    }
}
