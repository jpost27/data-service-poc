package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "event_associations")
@ToString
@EqualsAndHashCode
public class FdEventAssociations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_association_id", nullable = false, unique = true)
    private Integer eventAssociationId;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @Column(name = "event_association_type_code", nullable = false, insertable = false, updatable = false)
    private String eventAssociationTypeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_association_type_code")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdEventAssociationTypes fdEventAssociationTypes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "events_event_associations",
            joinColumns = {@JoinColumn(name = "event_association_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdEvents> fdEvents;

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

    public FdEventAssociationTypes getFdEventAssociationTypes() {
        return fdEventAssociationTypes;
    }

    public void setFdEventAssociationTypes(FdEventAssociationTypes fdEventAssociationTypes) {
        this.fdEventAssociationTypes = fdEventAssociationTypes;
    }

    public List<FdEvents> getFdEvents() {
        return fdEvents;
    }

    public void setFdEvents(List<FdEvents> fdEvents) {
        this.fdEvents = fdEvents;
    }
}
