package com.jp.dataservice.model.dto;

public class EventsEventAssociations {
    private Integer eventAssociationId;

    private Integer eventId;

    private Events events;

    private EventAssociations eventAssociations;

    public Integer getEventAssociationId() {
        return eventAssociationId;
    }

    public void setEventAssociationId(Integer eventAssociationId) {
        this.eventAssociationId = eventAssociationId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public EventAssociations getEventAssociations() {
        return eventAssociations;
    }

    public void setEventAssociations(EventAssociations eventAssociations) {
        this.eventAssociations = eventAssociations;
    }
}
