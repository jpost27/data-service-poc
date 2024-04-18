package com.jp.dataservicepoc.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@IdClass(FdSportPositionId.class)
@Entity(name = "sport_positions")
public class FdSportPosition implements Serializable {
    @Id
    @Column(name = "sport_position_code")
    public String sportPositionCode;
    @Id
    @Column(name = "sport_id")
    public Integer sportId;

    @Column(name = "description")
    public Integer description;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "sport_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdSport fdSport;

}
