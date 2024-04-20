package com.jp.dataservicepoc.model.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class InjuryStatus implements Serializable {
    public String injuryStatusCode;
    public String description;
}
