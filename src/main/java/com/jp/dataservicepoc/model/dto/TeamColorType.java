package com.jp.dataservicepoc.model.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class TeamColorType implements Serializable {
    public String teamColorTypeCode; // PRIMARY, SECONDARY, etc

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TeamColor> teamColors;
}
