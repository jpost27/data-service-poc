package com.jp.dataservice.model.dto;

import java.util.List;

public class TeamAssociationTypes {
    private String teamAssociationTypeCode;

    private List<TeamAssociations> teamAssociations;

    public String getTeamAssociationTypeCode() {
        return teamAssociationTypeCode;
    }

    public void setTeamAssociationTypeCode(String teamAssociationTypeCode) {
        this.teamAssociationTypeCode = teamAssociationTypeCode;
    }

    public List<TeamAssociations> getTeamAssociations() {
        return teamAssociations;
    }

    public void setTeamAssociations(List<TeamAssociations> teamAssociations) {
        this.teamAssociations = teamAssociations;
    }
}
