package com.jp.dataservice.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "team_associations")
@ToString
@EqualsAndHashCode
public class FdTeamAssociations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_association_id", nullable = false, unique = true)
    private Integer teamAssociationId;

    @Column(name = "name", unique = true)
    @Nullable
    private String name;

    @Column(name = "short_name")
    @Nullable
    private String shortName;

    @Column(name = "team_association_type_code", nullable = false, insertable = false, updatable = false)
    private String teamAssociationTypeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_association_type_code")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeamAssociationTypes fdTeamAssociationTypes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "team_team_associations",
            joinColumns = {@JoinColumn(name = "team_association_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdTeams> fdTeams;

    public Integer getTeamAssociationId() {
        return teamAssociationId;
    }

    public void setTeamAssociationId(Integer teamAssociationId) {
        this.teamAssociationId = teamAssociationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTeamAssociationTypeCode() {
        return teamAssociationTypeCode;
    }

    public void setTeamAssociationTypeCode(String teamAssociationTypeCode) {
        this.teamAssociationTypeCode = teamAssociationTypeCode;
    }

    public FdTeamAssociationTypes getFdTeamAssociationTypes() {
        return fdTeamAssociationTypes;
    }

    public void setFdTeamAssociationTypes(FdTeamAssociationTypes fdTeamAssociationTypes) {
        this.fdTeamAssociationTypes = fdTeamAssociationTypes;
    }

    public List<FdTeams> getFdTeams() {
        return fdTeams;
    }

    public void setFdTeams(List<FdTeams> fdTeams) {
        this.fdTeams = fdTeams;
    }
}
