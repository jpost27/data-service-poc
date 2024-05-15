package com.jp.dataservice.model.dto;

import java.util.List;

public class InjuryStatuses {
    private String injuryStatusCode;

    private String description;

    private List<Competitors> competitors;

    public String getInjuryStatusCode() {
        return injuryStatusCode;
    }

    public void setInjuryStatusCode(String injuryStatusCode) {
        this.injuryStatusCode = injuryStatusCode;
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
