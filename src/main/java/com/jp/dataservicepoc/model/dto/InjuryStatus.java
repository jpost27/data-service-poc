package com.jp.dataservicepoc.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class InjuryStatus implements Serializable {
    public String injuryStatusCode;
    public String description;

}
