package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
public class TeamSportsbookSelectionId implements Serializable {
    private Long selectionId;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TeamProviderId teamProviderIds;
}
