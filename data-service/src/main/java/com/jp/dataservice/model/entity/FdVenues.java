package com.jp.dataservice.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "venues")
@ToString
@EqualsAndHashCode
public class FdVenues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venue_id", nullable = false, unique = true)
    private Integer venueId;

    @Column(name = "capacity")
    @Nullable
    private Integer capacity;

    @Column(name = "latitude")
    @Nullable
    private BigDecimal latitude;

    @Column(name = "longitude")
    @Nullable
    private BigDecimal longitude;

    @Column(name = "address")
    @Nullable
    private String address;

    @Column(name = "city")
    @Nullable
    private String city;

    @Column(name = "country")
    @Nullable
    private String country;

    @Column(name = "name")
    @Nullable
    private String name;

    @Column(name = "roof_type")
    @Nullable
    private String roofType;

    @Column(name = "state")
    @Nullable
    private String state;

    @Column(name = "surface")
    @Nullable
    private String surface;

    @Column(name = "zip")
    @Nullable
    private String zip;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdVenues")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdEvents> fdEvents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdVenues")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdTeams> fdTeams;

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<FdEvents> getFdEvents() {
        return fdEvents;
    }

    public void setFdEvents(List<FdEvents> fdEvents) {
        this.fdEvents = fdEvents;
    }

    public List<FdTeams> getFdTeams() {
        return fdTeams;
    }

    public void setFdTeams(List<FdTeams> fdTeams) {
        this.fdTeams = fdTeams;
    }
}
