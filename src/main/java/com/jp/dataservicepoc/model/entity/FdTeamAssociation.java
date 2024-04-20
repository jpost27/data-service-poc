package com.jp.dataservicepoc.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import lombok.Data;

@Data
@Entity(name = "team_associations")
public class FdTeamAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_association_id")
    private Integer teamAssociationId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_association_type_code", nullable = false)
    private FdTeamAssociationType fdTeamAssociationType;

    @Column(name = "name", unique = true)
    private String name; // National Football Conference

    @Column(name = "short_name")
    private String shortName; // NFC

    @ManyToMany(mappedBy = "fdTeamAssociations", fetch = FetchType.LAZY)
    private List<FdTeam> fdTeams;
}
