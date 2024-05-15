package com.jp.dataservice.model.dto;

public class CompetitorSportsbookSelectionIds {
    private Long selectionId;

    private Integer competitorId;

    private CompetitorProviderIds competitorProviderIds;

    public Long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Long selectionId) {
        this.selectionId = selectionId;
    }

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }

    public CompetitorProviderIds getCompetitorProviderIds() {
        return competitorProviderIds;
    }

    public void setCompetitorProviderIds(CompetitorProviderIds competitorProviderIds) {
        this.competitorProviderIds = competitorProviderIds;
    }
}
