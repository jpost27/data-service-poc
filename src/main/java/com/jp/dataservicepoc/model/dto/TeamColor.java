package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
public class TeamColor implements Serializable {
    private Integer teamColorId;
    private TeamColorType teamColorType;
    private String hexColor;
    private Integer alpha;
    private Integer rgbRed;
    private Integer rgbGreen;
    private Integer rgbBlue;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;

}
