package com.jp.dataservice.model.entity;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdSports")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdLeagues> fdLeagues;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdSports")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdSportPositions> fdSportPositions;

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

    public List<FdLeagues> getFdLeagues() {
        return fdLeagues;
    }

    public void setFdLeagues(List<FdLeagues> fdLeagues) {
        this.fdLeagues = fdLeagues;
    }

    public List<FdSportPositions> getFdSportPositions() {
        return fdSportPositions;
    }

    public void setFdSportPositions(List<FdSportPositions> fdSportPositions) {
        this.fdSportPositions = fdSportPositions;
    }
}
