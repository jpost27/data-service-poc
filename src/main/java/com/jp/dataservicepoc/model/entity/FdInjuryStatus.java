package com.jp.dataservicepoc.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity(name = "injury_statuses")
public class FdInjuryStatus {
    @Id
    @Column(name = "injury_status_code")
    public String injuryStatusCode;

    @Column(name = "description")
    public String description;
}
