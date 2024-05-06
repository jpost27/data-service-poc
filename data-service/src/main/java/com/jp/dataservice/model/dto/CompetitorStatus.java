package com.jp.dataservice.model.dto;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class CompetitorStatus {
    public String competitorStatusCode;
    public String description;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Competitor> competitors;
}
