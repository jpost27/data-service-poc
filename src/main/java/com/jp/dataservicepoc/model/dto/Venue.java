package com.jp.dataservicepoc.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class Venue implements Serializable {
    private Integer venueId;
    private String name;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String address;
    private Integer capacity;
    private String surface;
    private String roofType;
    private BigDecimal latitude;
    private BigDecimal longitude;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Team> teams;
}
