package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
public class CompetitorProviderId implements Serializable {
    private Integer competitorId;
    private Integer fanduelCompetitorId;
    private String sportradarCompetitorId;
    private Integer numberfireCompetitorId;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<CompetitorSportsbookSelectionId> sportsbookSelectionIds;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Competitor competitor;
}
