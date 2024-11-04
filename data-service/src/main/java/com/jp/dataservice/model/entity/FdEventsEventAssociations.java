package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "events_event_associations")
@ToString
@EqualsAndHashCode
public class FdEventsEventAssociations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_association_id", nullable = false, insertable = false, updatable = false)
    private Integer eventAssociationId;

    @Column(name = "event_id", nullable = false, insertable = false, updatable = false)
    private Integer eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdEvents fdEvents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_association_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdEventAssociations fdEventAssociations;

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

    public FdEvents getFdEvents() {
        return fdEvents;
    }

    public void setFdEvents(FdEvents fdEvents) {
        this.fdEvents = fdEvents;
    }

    public FdEventAssociations getFdEventAssociations() {
        return fdEventAssociations;
    }

    public void setFdEventAssociations(FdEventAssociations fdEventAssociations) {
        this.fdEventAssociations = fdEventAssociations;
    }
}
