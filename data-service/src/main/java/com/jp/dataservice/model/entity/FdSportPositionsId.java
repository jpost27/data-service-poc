package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class FdSportPositionsId {
    @Column(name = "sport_id")
    private Integer sportId;

    @Column(name = "sport_position_code")
    private String sportPositionCode;

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
}
