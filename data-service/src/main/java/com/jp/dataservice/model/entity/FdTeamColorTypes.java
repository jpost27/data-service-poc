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

@Entity(name = "team_color_types")
@ToString
@EqualsAndHashCode
public class FdTeamColorTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_color_type_code", nullable = false, unique = true)
    private String teamColorTypeCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fdTeamColorTypes")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FdTeamColors> fdTeamColors;

    public String getTeamColorTypeCode() {
        return teamColorTypeCode;
    }

    public void setTeamColorTypeCode(String teamColorTypeCode) {
        this.teamColorTypeCode = teamColorTypeCode;
    }

    public List<FdTeamColors> getFdTeamColors() {
        return fdTeamColors;
    }

    public void setFdTeamColors(List<FdTeamColors> fdTeamColors) {
        this.fdTeamColors = fdTeamColors;
    }
}
