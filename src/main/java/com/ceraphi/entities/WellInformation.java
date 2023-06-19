package com.ceraphi.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Setter
@Getter
@Table(name = "well_info")
@NoArgsConstructor
public class WellInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String siteName;
    private String coordinates;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wellInformation", orphanRemoval = true)
    private List<WellDetails> wellDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "general_Information_id")
    private GeneralInformation generalInformation;

    public WellInformation(Long id, String siteName, String coordinates, List<WellDetails> wellDetails, GeneralInformation generalInformation) {
        this.id = id;
        this.siteName = siteName;
        this.coordinates = coordinates;
        this.wellDetails = wellDetails;
        this.generalInformation = generalInformation;
    }

    public Long getId() {
        return id;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public List<WellDetails> getWellDetails() {
        return wellDetails;
    }

    public GeneralInformation getGeneralInformation() {
        return generalInformation;
    }
    public List<WellDetails> getWells() {
        return wellDetails;
    }


    public void setWells(List<WellDetails> wells) {
        this.wellDetails= wells;
    }

    public void addWellDetail(WellDetails well) {
        wellDetails.add(well);
        well.setWellInformation(this);
    }

    public void removeWell(WellDetails well) {
        wellDetails.remove(well);
        well.setWellInformation(null);
    }

}
