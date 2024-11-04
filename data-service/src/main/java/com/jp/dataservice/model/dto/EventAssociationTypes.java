package com.jp.dataservice.model.dto;

import java.util.List;

public class EventAssociationTypes {
    private String eventAssociationTypeCode;

    private List<EventAssociations> eventAssociations;

    public String getEventAssociationTypeCode() {
        return eventAssociationTypeCode;
    }

    public void setEventAssociationTypeCode(String eventAssociationTypeCode) {
        this.eventAssociationTypeCode = eventAssociationTypeCode;
    }

    public List<EventAssociations> getEventAssociations() {
        return eventAssociations;
    }

    public void setEventAssociations(List<EventAssociations> eventAssociations) {
        this.eventAssociations = eventAssociations;
    }
}
