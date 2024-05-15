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

@Entity(name = "injury_statuses")
@ToString
@EqualsAndHashCode
public class FdInjuryStatuses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "injury_status_code", nullable = false, unique = true)
    private String injuryStatusCode;

    @Column(name = "description")
    @Nullable
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdInjuryStatuses")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdCompetitors> fdCompetitors;

    public String getInjuryStatusCode() {
        return injuryStatusCode;
    }

    public void setInjuryStatusCode(String injuryStatusCode) {
        this.injuryStatusCode = injuryStatusCode;
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
