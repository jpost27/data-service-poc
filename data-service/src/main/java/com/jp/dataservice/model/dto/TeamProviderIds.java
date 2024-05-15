package com.jp.dataservice.model.dto;

import java.util.List;

public class TeamProviderIds {
    private Integer teamId;

    private Integer fanduelTeamId;

    private Integer numberfireTeamId;

    private String sportradarTeamId;

    private Teams teams;

    private List<TeamSportsbookSelectionIds> teamSportsbookSelectionIds;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getFanduelTeamId() {
        return fanduelTeamId;
    }

    public void setFanduelTeamId(Integer fanduelTeamId) {
        this.fanduelTeamId = fanduelTeamId;
    }

    public Integer getNumberfireTeamId() {
        return numberfireTeamId;
    }

    public void setNumberfireTeamId(Integer numberfireTeamId) {
        this.numberfireTeamId = numberfireTeamId;
    }

    public String getSportradarTeamId() {
        return sportradarTeamId;
    }

    public void setSportradarTeamId(String sportradarTeamId) {
        this.sportradarTeamId = sportradarTeamId;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public List<TeamSportsbookSelectionIds> getTeamSportsbookSelectionIds() {
        return teamSportsbookSelectionIds;
    }

    public void setTeamSportsbookSelectionIds(List<TeamSportsbookSelectionIds> teamSportsbookSelectionIds) {
        this.teamSportsbookSelectionIds = teamSportsbookSelectionIds;
    }
}
