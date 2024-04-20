package com.jp.dataservicepoc.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity(name = "team_association_types")
public class FdTeamAssociationType {
    @Id
    @Column(name = "team_association_type_code")
    public String teamAssociationTypeCode; // conference, division, league, etc

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdTeamAssociationType")
    private List<FdTeamAssociation> fdTeamAssociations;
}
