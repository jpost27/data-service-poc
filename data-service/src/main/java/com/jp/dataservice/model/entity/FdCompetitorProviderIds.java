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

@Entity(name = "competitor_provider_ids")
@ToString
@EqualsAndHashCode
public class FdCompetitorProviderIds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competitor_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer competitorId;

    @Column(name = "fanduel_competitor_id", unique = true)
    @Nullable
    private Integer fanduelCompetitorId;

    @Column(name = "numberfire_competitor_id")
    @Nullable
    private Integer numberfireCompetitorId;

    @Column(name = "sportradar_competitor_id", unique = true)
    @Nullable
    private String sportradarCompetitorId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "competitor_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdCompetitors fdCompetitors;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdCompetitorProviderIds")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdCompetitorSportsbookSelectionIds> fdCompetitorSportsbookSelectionIds;

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }

    public Integer getFanduelCompetitorId() {
        return fanduelCompetitorId;
    }

    public void setFanduelCompetitorId(Integer fanduelCompetitorId) {
        this.fanduelCompetitorId = fanduelCompetitorId;
    }

    public Integer getNumberfireCompetitorId() {
        return numberfireCompetitorId;
    }

    public void setNumberfireCompetitorId(Integer numberfireCompetitorId) {
        this.numberfireCompetitorId = numberfireCompetitorId;
    }

    public String getSportradarCompetitorId() {
        return sportradarCompetitorId;
    }

    public void setSportradarCompetitorId(String sportradarCompetitorId) {
        this.sportradarCompetitorId = sportradarCompetitorId;
    }

    public FdCompetitors getFdCompetitors() {
        return fdCompetitors;
    }

    public void setFdCompetitors(FdCompetitors fdCompetitors) {
        this.fdCompetitors = fdCompetitors;
    }

    public List<FdCompetitorSportsbookSelectionIds> getFdCompetitorSportsbookSelectionIds() {
        return fdCompetitorSportsbookSelectionIds;
    }

    public void setFdCompetitorSportsbookSelectionIds(
            List<FdCompetitorSportsbookSelectionIds> fdCompetitorSportsbookSelectionIds) {
        this.fdCompetitorSportsbookSelectionIds = fdCompetitorSportsbookSelectionIds;
    }
}
