package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "competitor_sportsbook_selection_ids")
public class FdCompetitorSportsbookSelectionId {
    @Id
    @Column(name = "selection_id")
    private Long selectionId;

    @ManyToOne
    @JoinColumn(name = "competitor_id", nullable = false)
    private FdCompetitorProviderId fdCompetitorProviderIds;
}
