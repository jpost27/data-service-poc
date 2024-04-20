package com.jp.dataservicepoc.model.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class Sport implements Serializable {
    private Integer sportId;
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<League> leagues;
}
