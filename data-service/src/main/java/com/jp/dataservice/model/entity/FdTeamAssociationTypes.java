package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "team_association_types")
@ToString
@EqualsAndHashCode
public class FdTeamAssociationTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_association_type_code", nullable = false, unique = true)
    private String teamAssociationTypeCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdTeamAssociationTypes")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdTeamAssociations> fdTeamAssociations;

    public String getTeamAssociationTypeCode() {
        return teamAssociationTypeCode;
    }

    public void setTeamAssociationTypeCode(String teamAssociationTypeCode) {
        this.teamAssociationTypeCode = teamAssociationTypeCode;
    }

    public List<FdTeamAssociations> getFdTeamAssociations() {
        return fdTeamAssociations;
    }

    public void setFdTeamAssociations(List<FdTeamAssociations> fdTeamAssociations) {
        this.fdTeamAssociations = fdTeamAssociations;
    }
}
