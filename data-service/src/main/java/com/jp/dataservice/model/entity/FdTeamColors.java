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
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "team_colors")
@ToString
@EqualsAndHashCode
public class FdTeamColors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_color_id", nullable = false, unique = true)
    private Integer teamColorId;

    @Column(name = "alpha")
    @Nullable
    private Integer alpha;

    @Column(name = "rgb_blue")
    @Nullable
    private Integer rgbBlue;

    @Column(name = "rgb_green")
    @Nullable
    private Integer rgbGreen;

    @Column(name = "rgb_red")
    @Nullable
    private Integer rgbRed;

    @Column(name = "team_id", nullable = false, insertable = false, updatable = false)
    private Integer teamId;

    @Column(name = "hex_color")
    @Nullable
    private String hexColor;

    @Column(name = "team_color_type_code", nullable = false, insertable = false, updatable = false)
    private String teamColorTypeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeams fdTeams;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_color_type_code")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeamColorTypes fdTeamColorTypes;

    public Integer getTeamColorId() {
        return teamColorId;
    }

    public void setTeamColorId(Integer teamColorId) {
        this.teamColorId = teamColorId;
    }

    public Integer getAlpha() {
        return alpha;
    }

    public void setAlpha(Integer alpha) {
        this.alpha = alpha;
    }

    public Integer getRgbBlue() {
        return rgbBlue;
    }

    public void setRgbBlue(Integer rgbBlue) {
        this.rgbBlue = rgbBlue;
    }

    public Integer getRgbGreen() {
        return rgbGreen;
    }

    public void setRgbGreen(Integer rgbGreen) {
        this.rgbGreen = rgbGreen;
    }

    public Integer getRgbRed() {
        return rgbRed;
    }

    public void setRgbRed(Integer rgbRed) {
        this.rgbRed = rgbRed;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public String getTeamColorTypeCode() {
        return teamColorTypeCode;
    }

    public void setTeamColorTypeCode(String teamColorTypeCode) {
        this.teamColorTypeCode = teamColorTypeCode;
    }

    public FdTeams getFdTeams() {
        return fdTeams;
    }

    public void setFdTeams(FdTeams fdTeams) {
        this.fdTeams = fdTeams;
    }

    public FdTeamColorTypes getFdTeamColorTypes() {
        return fdTeamColorTypes;
    }

    public void setFdTeamColorTypes(FdTeamColorTypes fdTeamColorTypes) {
        this.fdTeamColorTypes = fdTeamColorTypes;
    }
}
