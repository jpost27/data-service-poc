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
import java.sql.Timestamp;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "events")
@ToString
@EqualsAndHashCode
public class FdEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false, unique = true)
    private Integer eventId;

    @Column(name = "scheduled_time", nullable = false)
    private Timestamp scheduledTime;

    @Column(name = "event_status_code", nullable = false, insertable = false, updatable = false)
    private String eventStatusCode;

    @Column(name = "season_id", nullable = false, insertable = false, updatable = false)
    private Integer seasonId;

    @Column(name = "away_team_score")
    @Nullable
    private Integer awayTeamScore;

    @Column(name = "event_title")
    @Nullable
    private String eventTitle;

    @Column(name = "home_team_score")
    @Nullable
    private Integer homeTeamScore;

    @Column(name = "sequence")
    @Nullable
    private Integer sequence;

    @Column(name = "away_team_id", insertable = false, updatable = false)
    @Nullable
    private Integer awayTeamId;

    @Column(name = "home_team_id", insertable = false, updatable = false)
    @Nullable
    private Integer homeTeamId;

    @Column(name = "venue_id", insertable = false, updatable = false)
    @Nullable
    private Integer venueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdSeasons fdSeasons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeams fdHomeTeams;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeams fdAwayTeams;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_status_code")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdEventStatuses fdEventStatuses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdVenues fdVenues;

    @OneToOne(mappedBy = "fdEvents", optional = false)
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdEventProviderIds fdEventProviderIds;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "events_event_associations",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_association_id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdEventAssociations> fdEventAssociations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdEvents")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdCompetitorsEvents> fdCompetitorsEvents;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Timestamp getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Timestamp scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getEventStatusCode() {
        return eventStatusCode;
    }

    public void setEventStatusCode(String eventStatusCode) {
        this.eventStatusCode = eventStatusCode;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(Integer awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public FdSeasons getFdSeasons() {
        return fdSeasons;
    }

    public void setFdSeasons(FdSeasons fdSeasons) {
        this.fdSeasons = fdSeasons;
    }

    public FdTeams getFdAwayTeams() {
        return fdAwayTeams;
    }

    public void setFdAwayTeams(FdTeams awayFdTeams) {
        this.fdAwayTeams = awayFdTeams;
    }

    public FdTeams getFdHomeTeams() {
        return fdHomeTeams;
    }

    public void setFdHomeTeams(FdTeams homeFdTeams) {
        this.fdHomeTeams = homeFdTeams;
    }

    public FdEventStatuses getFdEventStatuses() {
        return fdEventStatuses;
    }

    public void setFdEventStatuses(FdEventStatuses fdEventStatuses) {
        this.fdEventStatuses = fdEventStatuses;
    }

    public FdVenues getFdVenues() {
        return fdVenues;
    }

    public void setFdVenues(FdVenues fdVenues) {
        this.fdVenues = fdVenues;
    }

    public FdEventProviderIds getFdEventProviderIds() {
        return fdEventProviderIds;
    }

    public void setFdEventProviderIds(FdEventProviderIds fdEventProviderIds) {
        this.fdEventProviderIds = fdEventProviderIds;
    }

    public List<FdEventAssociations> getFdEventAssociations() {
        return fdEventAssociations;
    }

    public void setFdEventAssociations(List<FdEventAssociations> fdEventAssociations) {
        this.fdEventAssociations = fdEventAssociations;
    }

    public List<FdCompetitorsEvents> getFdCompetitorsEvents() {
        return fdCompetitorsEvents;
    }

    public void setFdCompetitorsEvents(List<FdCompetitorsEvents> fdCompetitorsEvents) {
        this.fdCompetitorsEvents = fdCompetitorsEvents;
    }
}
