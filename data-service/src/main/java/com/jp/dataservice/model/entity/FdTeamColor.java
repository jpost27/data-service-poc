package com.jp.dataservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity(name = "team_colors")
public class FdTeamColor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_color_id")
    private Integer teamColorId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_color_type_code", nullable = false)
    private FdTeamColorType fdTeamColorType;

    @Column(name = "hex_color")
    private String hexColor;

    @Column(name = "alpha")
    private Integer alpha;

    @Column(name = "rgb_red")
    private Integer rgbRed;

    @Column(name = "rgb_green")
    private Integer rgbGreen;

    @Column(name = "rgb_blue")
    private Integer rgbBlue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FdTeam fdTeam;
}
