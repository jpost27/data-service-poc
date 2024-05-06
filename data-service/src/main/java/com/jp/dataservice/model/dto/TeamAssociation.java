package com.jp.dataservice.model.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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