package com.jp.dataservice.model.dto;

public class EventProviderIds {
    private Integer eventId;

    private Integer fanduelEventId;

    private Integer numberfireEventId;

    private Integer sportsbookEventId;

    private String sportradarEventId;

    private Events events;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getFanduelEventId() {
        return fanduelEventId;
    }

    public void setFanduelEventId(Integer fanduelEventId) {
        this.fanduelEventId = fanduelEventId;
    }

    public Integer getNumberfireEventId() {
        return numberfireEventId;
    }

    public void setNumberfireEventId(Integer numberfireEventId) {
        this.numberfireEventId = numberfireEventId;
    }

    public Integer getSportsbookEventId() {
        return sportsbookEventId;
    }

    public void setSportsbookEventId(Integer sportsbookEventId) {
        this.sportsbookEventId = sportsbookEventId;
    }

    public String getSportradarEventId() {
        return sportradarEventId;
    }

    public void setSportradarEventId(String sportradarEventId) {
        this.sportradarEventId = sportradarEventId;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }
}
