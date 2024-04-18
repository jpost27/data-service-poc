package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
public class TeamColorType implements Serializable {
    public String teamColorTypeCode; // PRIMARY, SECONDARY, etc
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TeamColor> teamColors;

}
