package com.jp.dataservicepoc.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@Entity(name = "competitor_provider_ids")
public class FdCompetitorProviderId {
    @Id
    @Column(name = "competitor_id")
    private Integer competitorId;

    @Column(name = "fanduel_competitor_id", unique = true)
    private Integer fanduelCompetitorId;

    @Column(name = "sportradar_competitor_id", unique = true)
    private String sportradarCompetitorId;

    @Column(name = "numberfire_competitor_id")
    private Integer numberfireCompetitorId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdCompetitorProviderIds")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<FdCompetitorSportsbookSelectionId> fdSportsbookSelectionIds;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "competitor_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private FdCompetitor fdCompetitor;
}
