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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "teams")
@ToString
@EqualsAndHashCode
public class FdTeams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", nullable = false, unique = true)
    private Integer teamId;

    @Column(name = "league_id", nullable = false, insertable = false, updatable = false)
    private Integer leagueId;

    @Column(name = "primary_venue_id", insertable = false, updatable = false)
    @Nullable
    private Integer primaryVenueId;

    @Column(name = "year_founded")
    @Nullable
    private Integer yearFounded;

    @Column(name = "abbreviation")
    @Nullable
    private String abbreviation;

    @Column(name = "market")
    @Nullable
    private String market;

    @Column(name = "name")
    @Nullable
    private String name;

    @Column(name = "owner")
    @Nullable
    private String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdLeagues fdLeagues;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_venue_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdVenues fdVenues;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdTeams")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdTeamColors> fdTeamColors;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdTeams")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdCompetitors> fdCompetitors;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "fdTeams", optional = false)
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeamProviderIds fdTeamProviderIds;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "team_team_associations",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_association_id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdTeamAssociations> fdTeamAssociations;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getPrimaryVenueId() {
        return primaryVenueId;
    }

    public void setPrimaryVenueId(Integer primaryVenueId) {
        this.primaryVenueId = primaryVenueId;
    }

    public Integer getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(Integer yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public FdLeagues getFdLeagues() {
        return fdLeagues;
    }

    public void setFdLeagues(FdLeagues fdLeagues) {
        this.fdLeagues = fdLeagues;
    }

    public FdVenues getFdVenues() {
        return fdVenues;
    }

    public void setFdVenues(FdVenues fdVenues) {
        this.fdVenues = fdVenues;
    }

    public List<FdTeamColors> getFdTeamColors() {
        return fdTeamColors;
    }

    public void setFdTeamColors(List<FdTeamColors> fdTeamColors) {
        this.fdTeamColors = fdTeamColors;
    }

    public List<FdCompetitors> getFdCompetitors() {
        return fdCompetitors;
    }

    public void setFdCompetitors(List<FdCompetitors> fdCompetitors) {
        this.fdCompetitors = fdCompetitors;
    }

    public FdTeamProviderIds getFdTeamProviderIds() {
        return fdTeamProviderIds;
    }

    public void setFdTeamProviderIds(FdTeamProviderIds fdTeamProviderIds) {
        this.fdTeamProviderIds = fdTeamProviderIds;
    }

    public List<FdTeamAssociations> getFdTeamAssociations() {
        return fdTeamAssociations;
    }

    public void setFdTeamAssociations(List<FdTeamAssociations> fdTeamAssociations) {
        this.fdTeamAssociations = fdTeamAssociations;
    }
}
