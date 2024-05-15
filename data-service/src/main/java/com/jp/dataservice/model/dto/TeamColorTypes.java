package com.jp.dataservice.model.dto;

import java.util.List;

public class TeamColorTypes {
    private String teamColorTypeCode;

    private List<TeamColors> teamColors;

    public String getTeamColorTypeCode() {
        return teamColorTypeCode;
    }

    public void setTeamColorTypeCode(String teamColorTypeCode) {
        this.teamColorTypeCode = teamColorTypeCode;
    }

    public List<TeamColors> getTeamColors() {
        return teamColors;
    }

    public void setTeamColors(List<TeamColors> teamColors) {
        this.teamColors = teamColors;
    }
}
