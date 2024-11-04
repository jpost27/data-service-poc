package com.jp.dataservice.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "event_provider_ids")
@ToString
@EqualsAndHashCode
public class FdEventProviderIds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer eventId;

    @Column(name = "fanduel_event_id")
    @Nullable
    private Integer fanduelEventId;

    @Column(name = "numberfire_event_id")
    @Nullable
    private Integer numberfireEventId;

    @Column(name = "sportsbook_event_id")
    @Nullable
    private Integer sportsbookEventId;

    @Column(name = "sportradar_event_id")
    @Nullable
    private String sportradarEventId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "event_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdEvents fdEvents;

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

    public FdEvents getFdEvents() {
        return fdEvents;
    }

    public void setFdEvents(FdEvents fdEvents) {
        this.fdEvents = fdEvents;
    }
}
