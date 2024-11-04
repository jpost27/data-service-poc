package com.jp.dataservice.model.entity;

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

@Entity(name = "event_association_types")
@ToString
@EqualsAndHashCode
public class FdEventAssociationTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_association_type_code", nullable = false, unique = true)
    private String eventAssociationTypeCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdEventAssociationTypes")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdEventAssociations> fdEventAssociations;

    public String getEventAssociationTypeCode() {
        return eventAssociationTypeCode;
    }

    public void setEventAssociationTypeCode(String eventAssociationTypeCode) {
        this.eventAssociationTypeCode = eventAssociationTypeCode;
    }

    public List<FdEventAssociations> getFdEventAssociations() {
        return fdEventAssociations;
    }

    public void setFdEventAssociations(List<FdEventAssociations> fdEventAssociations) {
        this.fdEventAssociations = fdEventAssociations;
    }
}
