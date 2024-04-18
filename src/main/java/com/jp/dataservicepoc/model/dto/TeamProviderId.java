package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
public class TeamProviderId implements Serializable {
    private Integer teamId;
    private Integer fanduelTeamId;
    private String sportradarTeamId;
    private Integer numberfireTeamId;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TeamSportsbookSelectionId> sportsbookSelectionIds;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;
}