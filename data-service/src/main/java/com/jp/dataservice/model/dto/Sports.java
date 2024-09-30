package com.jp.dataservice.model.dto;

import java.util.List;

public class Sports {
    private Integer sportId;

    private String name;

    private List<Leagues> leagues;

    private List<SportPositions> sportPositions;

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Leagues> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<Leagues> leagues) {
        this.leagues = leagues;
    }

    public List<SportPositions> getSportPositions() {
        return sportPositions;
    }

    public void setSportPositions(List<SportPositions> sportPositions) {
        this.sportPositions = sportPositions;
    }
}
