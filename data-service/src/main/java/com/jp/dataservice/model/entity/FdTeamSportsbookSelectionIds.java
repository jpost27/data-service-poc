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

@Entity(name = "team_sportsbook_selection_ids")
@ToString
@EqualsAndHashCode
public class FdTeamSportsbookSelectionIds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selection_id", nullable = false, unique = true)
    private Long selectionId;

    @Column(name = "team_id", nullable = false, insertable = false, updatable = false)
    private Integer teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeamProviderIds fdTeamProviderIds;

    public Long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Long selectionId) {
        this.selectionId = selectionId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public FdTeamProviderIds getFdTeamProviderIds() {
        return fdTeamProviderIds;
    }

    public void setFdTeamProviderIds(FdTeamProviderIds fdTeamProviderIds) {
        this.fdTeamProviderIds = fdTeamProviderIds;
    }
}
