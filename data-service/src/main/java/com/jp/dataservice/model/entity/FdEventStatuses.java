package com.jp.dataservice.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "event_statuses")
@ToString
@EqualsAndHashCode
public class FdEventStatuses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_status_code", nullable = false, unique = true)
    private String eventStatusCode;

    @Column(name = "description")
    @Nullable
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdEventStatuses")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdEvents> fdEvents;

    public String getEventStatusCode() {
        return eventStatusCode;
    }

    public void setEventStatusCode(String eventStatusCode) {
        this.eventStatusCode = eventStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FdEvents> getFdEvents() {
        return fdEvents;
    }

    public void setFdEvents(List<FdEvents> fdEvents) {
        this.fdEvents = fdEvents;
    }
}
