package com.jp.dataservice.model.dto;

import java.util.List;

public class Teams {
    private Integer teamId;

    private Integer leagueId;

    private Integer primaryVenueId;

    private Integer yearFounded;

    private String abbreviation;

    private String market;

    private String name;

    private String owner;

    private Venues venues;

    private Leagues leagues;

    private List<TeamColors> teamColors;

    private List<Competitors> competitors;

    private TeamProviderIds teamProviderIds;

    private List<TeamAssociations> teamAssociations;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getPrimaryVenueId() {
        return primaryVenueId;
    }

    public void setPrimaryVenueId(Integer primaryVenueId) {
        this.primaryVenueId = primaryVenueId;
    }

    public Integer getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(Integer yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Venues getVenues() {
        return venues;
    }

    public void setVenues(Venues venues) {
        this.venues = venues;
    }

    public Leagues getLeagues() {
        return leagues;
    }

    public void setLeagues(Leagues leagues) {
        this.leagues = leagues;
    }

    public List<TeamColors> getTeamColors() {
        return teamColors;
    }

    public void setTeamColors(List<TeamColors> teamColors) {
        this.teamColors = teamColors;
    }

    public List<Competitors> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<Competitors> competitors) {
        this.competitors = competitors;
    }

    public TeamProviderIds getTeamProviderIds() {
        return teamProviderIds;
    }

    public void setTeamProviderIds(TeamProviderIds teamProviderIds) {
        this.teamProviderIds = teamProviderIds;
    }

    public List<TeamAssociations> getTeamAssociations() {
        return teamAssociations;
    }

    public void setTeamAssociations(List<TeamAssociations> teamAssociations) {
        this.teamAssociations = teamAssociations;
    }
}
