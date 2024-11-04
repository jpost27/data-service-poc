package com.jp.dataservice.model.dto;

import java.util.List;

public class EventAssociations {
    private Integer eventAssociationId;

    private String description;

    private String eventAssociationTypeCode;

    private EventAssociationTypes eventAssociationTypes;

    private List<Events> events;

    public Integer getEventAssociationId() {
        return eventAssociationId;
    }

    public void setEventAssociationId(Integer eventAssociationId) {
        this.eventAssociationId = eventAssociationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventAssociationTypeCode() {
        return eventAssociationTypeCode;
    }

    public void setEventAssociationTypeCode(String eventAssociationTypeCode) {
        this.eventAssociationTypeCode = eventAssociationTypeCode;
    }

    public EventAssociationTypes getEventAssociationTypes() {
        return eventAssociationTypes;
    }

    public void setEventAssociationTypes(EventAssociationTypes eventAssociationTypes) {
        this.eventAssociationTypes = eventAssociationTypes;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }
}
