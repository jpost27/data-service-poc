package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class League implements Serializable {

    private Integer leagueId;
    private String abbreviatedName;
    private String fullName;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Sport sport;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Team> teams;

}
