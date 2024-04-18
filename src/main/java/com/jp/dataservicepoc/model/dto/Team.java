package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
public class Team implements Serializable {
    private Integer teamId;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private League league;

    private String name;
    private String abbreviation;

    private String market;

    private Integer yearFounded;

    private String owner;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TeamProviderId teamProviderIds;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TeamAssociation> teamAssociations;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TeamColor> teamColors;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Venue primaryVenue;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Competitor> competitors;

}
