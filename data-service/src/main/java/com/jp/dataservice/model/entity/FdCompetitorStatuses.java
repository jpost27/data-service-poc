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

@Entity(name = "competitor_statuses")
@ToString
@EqualsAndHashCode
public class FdCompetitorStatuses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competitor_status_code", nullable = false, unique = true)
    private String competitorStatusCode;

    @Column(name = "description")
    @Nullable
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdCompetitorStatuses")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdCompetitors> fdCompetitors;

    public String getCompetitorStatusCode() {
        return competitorStatusCode;
    }

    public void setCompetitorStatusCode(String competitorStatusCode) {
        this.competitorStatusCode = competitorStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FdCompetitors> getFdCompetitors() {
        return fdCompetitors;
    }

    public void setFdCompetitors(List<FdCompetitors> fdCompetitors) {
        this.fdCompetitors = fdCompetitors;
    }
}
