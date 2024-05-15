package com.jp.dataservice.model.dto;

import java.util.List;

public class CompetitorProviderIds {
    private Integer competitorId;

    private Integer fanduelCompetitorId;

    private Integer numberfireCompetitorId;

    private String sportradarCompetitorId;

    private Competitors competitors;

    private List<CompetitorSportsbookSelectionIds> competitorSportsbookSelectionIds;

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }

    public Integer getFanduelCompetitorId() {
        return fanduelCompetitorId;
    }

    public void setFanduelCompetitorId(Integer fanduelCompetitorId) {
        this.fanduelCompetitorId = fanduelCompetitorId;
    }

    public Integer getNumberfireCompetitorId() {
        return numberfireCompetitorId;
    }

    public void setNumberfireCompetitorId(Integer numberfireCompetitorId) {
        this.numberfireCompetitorId = numberfireCompetitorId;
    }

    public String getSportradarCompetitorId() {
        return sportradarCompetitorId;
    }

    public void setSportradarCompetitorId(String sportradarCompetitorId) {
        this.sportradarCompetitorId = sportradarCompetitorId;
    }

    public Competitors getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Competitors competitors) {
        this.competitors = competitors;
    }

    public List<CompetitorSportsbookSelectionIds> getCompetitorSportsbookSelectionIds() {
        return competitorSportsbookSelectionIds;
    }

    public void setCompetitorSportsbookSelectionIds(
            List<CompetitorSportsbookSelectionIds> competitorSportsbookSelectionIds) {
        this.competitorSportsbookSelectionIds = competitorSportsbookSelectionIds;
    }
}
