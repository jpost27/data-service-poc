package com.jp.dataservicepoc.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Entity(name = "competitor_statuses")
public class FdCompetitorStatus {
    @Id
    @Column(name = "competitor_status_code")
    public String competitorStatusCode;

    @Column(name = "description")
    public String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdCompetitorStatus")
    private List<FdCompetitor> fdompetitors;

}
