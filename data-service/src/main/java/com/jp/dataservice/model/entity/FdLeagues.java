package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "leagues")
@ToString
@EqualsAndHashCode
public class FdLeagues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_id", nullable = false, unique = true)
    private Integer leagueId;

    @Column(name = "sport_id", nullable = false, insertable = false, updatable = false)
    private Integer sportId;

    @Column(name = "abbreviated_name", nullable = false)
    private String abbreviatedName;

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdSports fdSports;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdLeagues")
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdSeasons> fdSeasons;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdLeagues")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdCompetitors> fdCompetitors;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdLeagues")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdTeams> fdTeams;

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    public void setAbbreviatedName(String abbreviatedName) {
        this.abbreviatedName = abbreviatedName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public FdSports getFdSports() {
        return fdSports;
    }

    public void setFdSports(FdSports fdSports) {
        this.fdSports = fdSports;
    }

    public List<FdSeasons> getFdSeasons() {
        return fdSeasons;
    }

    public void setFdSeasons(List<FdSeasons> fdSeasons) {
        this.fdSeasons = fdSeasons;
    }

    public List<FdCompetitors> getFdCompetitors() {
        return fdCompetitors;
    }

    public void setFdCompetitors(List<FdCompetitors> fdCompetitors) {
        this.fdCompetitors = fdCompetitors;
    }

    public List<FdTeams> getFdTeams() {
        return fdTeams;
    }

    public void setFdTeams(List<FdTeams> fdTeams) {
        this.fdTeams = fdTeams;
    }
}
