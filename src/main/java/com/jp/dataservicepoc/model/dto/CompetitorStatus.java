package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
public class CompetitorStatus {
    public String competitorStatusCode;
    public String description;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Competitor> competitors;

}
