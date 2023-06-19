package com.ceraphi.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
@Data
public class WellInfoDto {
    private String siteName;
    private String coordinates;
    private List<WellDto> wells;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public List<WellDto> getWells() {
        return wells;
    }

    public void setWells(List<WellDto> wells) {
        this.wells = wells;
    }
// Getters and setters...
}
