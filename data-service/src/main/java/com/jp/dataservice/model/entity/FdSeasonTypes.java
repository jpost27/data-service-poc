package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "season_types")
@ToString
@EqualsAndHashCode
public class FdSeasonTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_type_code", nullable = false, unique = true)
    private String seasonTypeCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdSeasonTypes")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdSeasons> fdSeasons;

    public String getSeasonTypeCode() {
        return seasonTypeCode;
    }

    public void setSeasonTypeCode(String seasonTypeCode) {
        this.seasonTypeCode = seasonTypeCode;
    }

    public List<FdSeasons> getFdSeasons() {
        return fdSeasons;
    }

    public void setFdSeasons(List<FdSeasons> fdSeasons) {
        this.fdSeasons = fdSeasons;
    }
}
