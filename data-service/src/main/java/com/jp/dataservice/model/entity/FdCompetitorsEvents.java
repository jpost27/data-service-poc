package com.jp.dataservice.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "competitors_events")
@ToString
@EqualsAndHashCode
public class FdCompetitorsEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competitors_events_id", nullable = false, unique = true)
    private Integer competitorsEventsId;

    @Column(name = "competitor_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer competitorId;

    @Column(name = "event_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer eventId;

    @Column(name = "team_id", unique = true, insertable = false, updatable = false)
    @Nullable
    private Integer teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competitor_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdCompetitors fdCompetitors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdEvents fdEvents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeams fdTeams;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdCompetitorsEvents")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdCompetitorEventStatistics> fdCompetitorEventStatistics;

    public Integer getCompetitorsEventsId() {
        return competitorsEventsId;
    }

    public void setCompetitorsEventsId(Integer competitorsEventsId) {
        this.competitorsEventsId = competitorsEventsId;
    }

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public FdCompetitors getFdCompetitors() {
        return fdCompetitors;
    }

    public void setFdCompetitors(FdCompetitors fdCompetitors) {
        this.fdCompetitors = fdCompetitors;
    }

    public FdEvents getFdEvents() {
        return fdEvents;
    }

    public void setFdEvents(FdEvents fdEvents) {
        this.fdEvents = fdEvents;
    }

    public FdTeams getFdTeams() {
        return fdTeams;
    }

    public void setFdTeams(FdTeams fdTeams) {
        this.fdTeams = fdTeams;
    }

    public List<FdCompetitorEventStatistics> getFdCompetitorEventStatistics() {
        return fdCompetitorEventStatistics;
    }

    public void setFdCompetitorEventStatistics(List<FdCompetitorEventStatistics> fdCompetitorEventStatistics) {
        this.fdCompetitorEventStatistics = fdCompetitorEventStatistics;
    }
}
