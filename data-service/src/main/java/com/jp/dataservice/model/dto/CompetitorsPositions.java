package com.jp.dataservice.model.dto;

public class CompetitorsPositions {
    private Integer competitorId;

    private Integer sportPositionCode;

    private String sportId;

    private Competitors competitors;

    private SportPositions sportPositions;

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }

    public Integer getSportPositionCode() {
        return sportPositionCode;
    }

    public void setSportPositionCode(Integer sportPositionCode) {
        this.sportPositionCode = sportPositionCode;
    }

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId;
    }

    public Competitors getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Competitors competitors) {
        this.competitors = competitors;
    }

    public SportPositions getSportPositions() {
        return sportPositions;
    }

    public void setSportPositions(SportPositions sportPositions) {
        this.sportPositions = sportPositions;
    }
}
