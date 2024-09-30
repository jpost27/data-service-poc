package com.jp.dataservice.model.dto;

import java.sql.Date;
import java.util.List;

public class Competitors {
    private Integer competitorId;

    private Date dateOfBirth;

    private Integer heightInInches;

    private Integer leagueId;

    private Integer teamId;

    private Integer weightInPounds;

    private String college;

    private String competitorStatusCode;

    private String firstName;

    private String highSchool;

    private String injuryStatusId;

    private String lastName;

    private String uniformIdentifier;

    private Teams teams;

    private CompetitorStatuses competitorStatuses;

    private InjuryStatuses injuryStatuses;

    private Leagues leagues;

    private CompetitorProviderIds competitorProviderIds;

    private List<SportPositions> sportPositions;

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

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public CompetitorStatuses getCompetitorStatuses() {
        return competitorStatuses;
    }

    public void setCompetitorStatuses(CompetitorStatuses competitorStatuses) {
        this.competitorStatuses = competitorStatuses;
    }

    public InjuryStatuses getInjuryStatuses() {
        return injuryStatuses;
    }

    public void setInjuryStatuses(InjuryStatuses injuryStatuses) {
        this.injuryStatuses = injuryStatuses;
    }

    public Leagues getLeagues() {
        return leagues;
    }

    public void setLeagues(Leagues leagues) {
        this.leagues = leagues;
    }

    public CompetitorProviderIds getCompetitorProviderIds() {
        return competitorProviderIds;
    }

    public void setCompetitorProviderIds(CompetitorProviderIds competitorProviderIds) {
        this.competitorProviderIds = competitorProviderIds;
    }

    public List<SportPositions> getSportPositions() {
        return sportPositions;
    }

    public void setSportPositions(List<SportPositions> sportPositions) {
        this.sportPositions = sportPositions;
    }
}
