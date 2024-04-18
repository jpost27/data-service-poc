package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class Competitor implements Serializable {

    private Integer competitorId;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private League league;
    private String firstName;
    private String lastName;
    private String uniformIdentifier;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private InjuryStatus injuryStatus;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CompetitorStatus competitorStatus;
    private LocalDate dateOfBirth;
    private Integer heightInInches;
    private Integer weightInPounds;
    private String college;
    private String highSchool;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CompetitorProviderId competitorProviderIds;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<SportPosition> positions;

}
