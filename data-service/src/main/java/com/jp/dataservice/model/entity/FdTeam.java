package com.jp.dataservice.model.entity;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity(name = "teams")
public class FdTeam {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdLeague fdLeague;

    @Column(name = "name")
    private String name;

    @Column(name = "abbreviation")
    private String abbreviation;

    @Column(name = "market")
    private String market;

    @Column(name = "year_founded")
    private Integer yearFounded;

    @Column(name = "owner")
    private String owner;

    @OneToOne(mappedBy = "fdTeam", optional = false, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private FdTeamProviderId fdTeamProviderIds;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "team_team_associations",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "team_association_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdTeamAssociation> fdTeamAssociations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdTeam")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdTeamColor> fdTeamColors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_venue_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdVenue fdPrimaryVenue;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdTeam")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdCompetitor> fdCompetitors;
}
