package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
public class TeamAssociation implements Serializable {
    private Integer teamAssociationId;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TeamAssociationType teamAssociationType;
    private String name; // National Football Conference
    private String shortName; // NFC
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Team> teams;
}
