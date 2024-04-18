package com.jp.dataservicepoc.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
public class SportPosition implements Serializable {
    public String sportPositionCode;
    public Integer sportId;
    public Integer description;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Sport sport;

}
