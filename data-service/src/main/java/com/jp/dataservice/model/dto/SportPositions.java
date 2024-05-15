package com.jp.dataservice.model.dto;

import java.util.List;

public class SportPositions {
    private Integer sportId;

    private String sportPositionCode;

    private Integer description;

    private Sports sports;

    private List<Competitors> competitors;

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public String getSportPositionCode() {
        return sportPositionCode;
    }

    public void setSportPositionCode(String sportPositionCode) {
        this.sportPositionCode = sportPositionCode;
    }

    public Integer getDescription() {
        return description;
    }

    public void setDescription(Integer description) {
        this.description = description;
    }

    public Sports getSports() {
        return sports;
    }

    public void setSports(Sports sports) {
        this.sports = sports;
    }

    public List<Competitors> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<Competitors> competitors) {
        this.competitors = competitors;
    }
}
