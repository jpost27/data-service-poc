package com.jp.dataservice.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "sport_positions")
@ToString
@EqualsAndHashCode
@IdClass(FdSportPositionsId.class)
public class FdSportPositions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sport_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer sportId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sport_position_code", nullable = false, unique = true)
    private String sportPositionCode;

    @Column(name = "description")
    @Nullable
    private Integer description;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "sport_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdSports fdSports;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "competitors_positions",
            joinColumns = {@JoinColumn(name = "sport_position_code"), @JoinColumn(name = "sport_id")},
            inverseJoinColumns = {@JoinColumn(name = "competitor_id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdCompetitors> fdCompetitors;

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public String getSportPositionCode() {
        return sportPositionCode;
    }

    public void setSportPositionCode(String sportPositionCode) {
        this.sportPositionCode = sportPositionCode;
    }

    public Integer getDescription() {
        return description;
    }

    public void setDescription(Integer description) {
        this.description = description;
    }

    public FdSports getFdSports() {
        return fdSports;
    }

    public void setFdSports(FdSports fdSports) {
        this.fdSports = fdSports;
    }

    public List<FdCompetitors> getFdCompetitors() {
        return fdCompetitors;
    }

    public void setFdCompetitors(List<FdCompetitors> fdCompetitors) {
        this.fdCompetitors = fdCompetitors;
    }
}
