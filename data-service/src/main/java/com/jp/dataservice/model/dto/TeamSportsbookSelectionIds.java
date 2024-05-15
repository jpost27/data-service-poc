package com.jp.dataservice.model.dto;

public class TeamSportsbookSelectionIds {
    private Long selectionId;

    private Integer teamId;

    private TeamProviderIds teamProviderIds;

    public Long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Long selectionId) {
        this.selectionId = selectionId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public TeamProviderIds getTeamProviderIds() {
        return teamProviderIds;
    }

    public void setTeamProviderIds(TeamProviderIds teamProviderIds) {
        this.teamProviderIds = teamProviderIds;
    }
}
