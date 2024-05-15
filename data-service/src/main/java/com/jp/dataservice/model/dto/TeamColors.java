package com.jp.dataservice.model.dto;

public class TeamColors {
    private Integer teamColorId;

    private Integer alpha;

    private Integer rgbBlue;

    private Integer rgbGreen;

    private Integer rgbRed;

    private Integer teamId;

    private String hexColor;

    private String teamColorTypeCode;

    private Teams teams;

    private TeamColorTypes teamColorTypes;

    public Integer getTeamColorId() {
        return teamColorId;
    }

    public void setTeamColorId(Integer teamColorId) {
        this.teamColorId = teamColorId;
    }

    public Integer getAlpha() {
        return alpha;
    }

    public void setAlpha(Integer alpha) {
        this.alpha = alpha;
    }

    public Integer getRgbBlue() {
        return rgbBlue;
    }

    public void setRgbBlue(Integer rgbBlue) {
        this.rgbBlue = rgbBlue;
    }

    public Integer getRgbGreen() {
        return rgbGreen;
    }

    public void setRgbGreen(Integer rgbGreen) {
        this.rgbGreen = rgbGreen;
    }

    public Integer getRgbRed() {
        return rgbRed;
    }

    public void setRgbRed(Integer rgbRed) {
        this.rgbRed = rgbRed;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public String getTeamColorTypeCode() {
        return teamColorTypeCode;
    }

    public void setTeamColorTypeCode(String teamColorTypeCode) {
        this.teamColorTypeCode = teamColorTypeCode;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public TeamColorTypes getTeamColorTypes() {
        return teamColorTypes;
    }

    public void setTeamColorTypes(TeamColorTypes teamColorTypes) {
        this.teamColorTypes = teamColorTypes;
    }
}
