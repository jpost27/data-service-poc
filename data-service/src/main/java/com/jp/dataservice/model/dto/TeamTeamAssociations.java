package com.jp.dataservice.model.dto;

public class TeamTeamAssociations {
    private Integer teamAssociationId;

    private Integer teamId;

    private Teams teams;

    private TeamAssociations teamAssociations;

    public Integer getTeamAssociationId() {
        return teamAssociationId;
    }

    public void setTeamAssociationId(Integer teamAssociationId) {
        this.teamAssociationId = teamAssociationId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public TeamAssociations getTeamAssociations() {
        return teamAssociations;
    }

    public void setTeamAssociations(TeamAssociations teamAssociations) {
        this.teamAssociations = teamAssociations;
    }
}
