package com.jp.dataservicepoc.model.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class TeamSportsbookSelectionId implements Serializable {
    private Long selectionId;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TeamProviderId teamProviderIds;
}
