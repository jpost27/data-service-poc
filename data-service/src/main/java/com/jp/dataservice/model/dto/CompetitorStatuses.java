package com.jp.dataservice.model.dto;

import java.util.List;

public class CompetitorStatuses {
    private String competitorStatusCode;

    private String description;

    private List<Competitors> competitors;

    public String getCompetitorStatusCode() {
        return competitorStatusCode;
    }

    public void setCompetitorStatusCode(String competitorStatusCode) {
        this.competitorStatusCode = competitorStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Competitors> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<Competitors> competitors) {
        this.competitors = competitors;
    }
}
