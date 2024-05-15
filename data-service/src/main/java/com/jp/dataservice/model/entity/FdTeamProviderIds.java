package com.jp.dataservice.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "team_provider_ids")
@ToString
@EqualsAndHashCode
public class FdTeamProviderIds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer teamId;

    @Column(name = "fanduel_team_id", unique = true)
    @Nullable
    private Integer fanduelTeamId;

    @Column(name = "numberfire_team_id")
    @Nullable
    private Integer numberfireTeamId;

    @Column(name = "sportradar_team_id", unique = true)
    @Nullable
    private String sportradarTeamId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "team_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeams fdTeams;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdTeamProviderIds")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdTeamSportsbookSelectionIds> fdTeamSportsbookSelectionIds;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getFanduelTeamId() {
        return fanduelTeamId;
    }

    public void setFanduelTeamId(Integer fanduelTeamId) {
        this.fanduelTeamId = fanduelTeamId;
    }

    public Integer getNumberfireTeamId() {
        return numberfireTeamId;
    }

    public void setNumberfireTeamId(Integer numberfireTeamId) {
        this.numberfireTeamId = numberfireTeamId;
    }

    public String getSportradarTeamId() {
        return sportradarTeamId;
    }

    public void setSportradarTeamId(String sportradarTeamId) {
        this.sportradarTeamId = sportradarTeamId;
    }

    public FdTeams getFdTeams() {
        return fdTeams;
    }

    public void setFdTeams(FdTeams fdTeams) {
        this.fdTeams = fdTeams;
    }

    public List<FdTeamSportsbookSelectionIds> getFdTeamSportsbookSelectionIds() {
        return fdTeamSportsbookSelectionIds;
    }

    public void setFdTeamSportsbookSelectionIds(List<FdTeamSportsbookSelectionIds> fdTeamSportsbookSelectionIds) {
        this.fdTeamSportsbookSelectionIds = fdTeamSportsbookSelectionIds;
    }
}
