package com.jp.dataservice.model.entity;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity(name = "competitors")
public class FdCompetitor {

    @Id
    @Column(name = "competitor_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer competitorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeam fdTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdLeague fdLeague;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "uniform_identifier")
    private String uniformIdentifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "injury_status_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdInjuryStatus fdInjuryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competitor_status_code")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdCompetitorStatus fdCompetitorStatus;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "height_in_inches")
    private Integer heightInInches;

    @Column(name = "weight_in_pounds")
    private Integer weightInPounds;

    @Column(name = "college")
    private String college;

    @Column(name = "high_school")
    private String highSchool;

    @OneToOne(mappedBy = "fdCompetitor", optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private FdCompetitorProviderId fdCompetitorProviderIds;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "competitors_positions",
            joinColumns = @JoinColumn(name = "competitor_id"),
            inverseJoinColumns = {@JoinColumn(name = "sport_position_code"), @JoinColumn(name = "sport_id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdSportPosition> fdPositions;
}
