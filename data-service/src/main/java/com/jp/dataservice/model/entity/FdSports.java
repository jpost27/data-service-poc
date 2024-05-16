package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "sports")
@ToString
@EqualsAndHashCode
public class FdSports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sport_id", nullable = false, unique = true)
    private Integer sportId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "fdSports", optional = false)
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdSportPositions fdSportPositions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdSports")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdLeagues> fdLeagues;

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FdSportPositions getFdSportPositions() {
        return fdSportPositions;
    }

    public void setFdSportPositions(FdSportPositions fdSportPositions) {
        this.fdSportPositions = fdSportPositions;
    }

    public List<FdLeagues> getFdLeagues() {
        return fdLeagues;
    }

    public void setFdLeagues(List<FdLeagues> fdLeagues) {
        this.fdLeagues = fdLeagues;
    }
}
