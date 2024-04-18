package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class TeamAssociationType implements Serializable {

    public String teamAssociationTypeCode; //conference, division, league, etc

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TeamAssociation> teamAssociations;

}
