package com.jp.dataservice.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.sql.Date;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "competitors")
@ToString
@EqualsAndHashCode
public class FdCompetitors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competitor_id", nullable = false, unique = true)
    private Integer competitorId;

    @Column(name = "date_of_birth")
    @Nullable
    private Date dateOfBirth;

    @Column(name = "height_in_inches")
    @Nullable
    private Integer heightInInches;

    @Column(name = "league_id", nullable = false, insertable = false, updatable = false)
    private Integer leagueId;

    @Column(name = "team_id", insertable = false, updatable = false)
    @Nullable
    private Integer teamId;

    @Column(name = "weight_in_pounds")
    @Nullable
    private Integer weightInPounds;

    @Column(name = "college")
    @Nullable
    private String college;

    @Column(name = "competitor_status_code", insertable = false, updatable = false)
    @Nullable
    private String competitorStatusCode;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "high_school")
    @Nullable
    private String highSchool;

    @Column(name = "injury_status_id", insertable = false, updatable = false)
    @Nullable
    private String injuryStatusId;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "uniform_identifier")
    @Nullable
    private String uniformIdentifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeams fdTeams;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competitor_status_code")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdCompetitorStatuses fdCompetitorStatuses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "injury_status_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdInjuryStatuses fdInjuryStatuses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdLeagues fdLeagues;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "fdCompetitors", optional = false)
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdCompetitorProviderIds fdCompetitorProviderIds;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "competitors_positions",
            joinColumns = {@JoinColumn(name = "competitor_id")},
            inverseJoinColumns = {@JoinColumn(name = "sport_position_code"), @JoinColumn(name = "sport_id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdSportPositions> fdSportPositions;

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(Integer heightInInches) {
        this.heightInInches = heightInInches;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(Integer weightInPounds) {
        this.weightInPounds = weightInPounds;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCompetitorStatusCode() {
        return competitorStatusCode;
    }

    public void setCompetitorStatusCode(String competitorStatusCode) {
        this.competitorStatusCode = competitorStatusCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public String getInjuryStatusId() {
        return injuryStatusId;
    }

    public void setInjuryStatusId(String injuryStatusId) {
        this.injuryStatusId = injuryStatusId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUniformIdentifier() {
        return uniformIdentifier;
    }

    public void setUniformIdentifier(String uniformIdentifier) {
        this.uniformIdentifier = uniformIdentifier;
    }

    public FdTeams getFdTeams() {
        return fdTeams;
    }

    public void setFdTeams(FdTeams fdTeams) {
        this.fdTeams = fdTeams;
    }

    public FdCompetitorStatuses getFdCompetitorStatuses() {
        return fdCompetitorStatuses;
    }

    public void setFdCompetitorStatuses(FdCompetitorStatuses fdCompetitorStatuses) {
        this.fdCompetitorStatuses = fdCompetitorStatuses;
    }

    public FdInjuryStatuses getFdInjuryStatuses() {
        return fdInjuryStatuses;
    }

    public void setFdInjuryStatuses(FdInjuryStatuses fdInjuryStatuses) {
        this.fdInjuryStatuses = fdInjuryStatuses;
    }

    public FdLeagues getFdLeagues() {
        return fdLeagues;
    }

    public void setFdLeagues(FdLeagues fdLeagues) {
        this.fdLeagues = fdLeagues;
    }

    public FdCompetitorProviderIds getFdCompetitorProviderIds() {
        return fdCompetitorProviderIds;
    }

    public void setFdCompetitorProviderIds(FdCompetitorProviderIds fdCompetitorProviderIds) {
        this.fdCompetitorProviderIds = fdCompetitorProviderIds;
    }

    public List<FdSportPositions> getFdSportPositions() {
        return fdSportPositions;
    }

    public void setFdSportPositions(List<FdSportPositions> fdSportPositions) {
        this.fdSportPositions = fdSportPositions;
    }
}
