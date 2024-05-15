package com.jp.dataservice.model.dto;

import java.util.List;

public class TeamAssociations {
    private Integer teamAssociationId;

    private String name;

    private String shortName;

    private String teamAssociationTypeCode;

    private TeamAssociationTypes teamAssociationTypes;

    private List<Teams> teams;

    public Integer getTeamAssociationId() {
        return teamAssociationId;
    }

    public void setTeamAssociationId(Integer teamAssociationId) {
        this.teamAssociationId = teamAssociationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTeamAssociationTypeCode() {
        return teamAssociationTypeCode;
    }

    public void setTeamAssociationTypeCode(String teamAssociationTypeCode) {
        this.teamAssociationTypeCode = teamAssociationTypeCode;
    }

    public TeamAssociationTypes getTeamAssociationTypes() {
        return teamAssociationTypes;
    }

    public void setTeamAssociationTypes(TeamAssociationTypes teamAssociationTypes) {
        this.teamAssociationTypes = teamAssociationTypes;
    }

    public List<Teams> getTeams() {
        return teams;
    }

    public void setTeams(List<Teams> teams) {
        this.teams = teams;
    }
}
