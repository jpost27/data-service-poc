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

@Entity(name = "competitor_sportsbook_selection_ids")
@ToString
@EqualsAndHashCode
public class FdCompetitorSportsbookSelectionIds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selection_id", nullable = false, unique = true)
    private Long selectionId;

    @Column(name = "competitor_id", nullable = false, insertable = false, updatable = false)
    private Integer competitorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competitor_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdCompetitorProviderIds fdCompetitorProviderIds;

    public Long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Long selectionId) {
        this.selectionId = selectionId;
    }

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }

    public FdCompetitorProviderIds getFdCompetitorProviderIds() {
        return fdCompetitorProviderIds;
    }

    public void setFdCompetitorProviderIds(FdCompetitorProviderIds fdCompetitorProviderIds) {
        this.fdCompetitorProviderIds = fdCompetitorProviderIds;
    }
}
