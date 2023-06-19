package com.ceraphi.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "general_info")
@Data
@Getter
@Setter
@NoArgsConstructor
public class GeneralInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String clientId;
    private String address;
    private String country;
    private String projectCurrency;
    private String preferredUnits;
    private String clientName;
    private String city;
    private String postalCode;
    @Lob
    private String GeoPoliticalData;
    @Lob
    private String restrictionDetails;
    @OneToMany(mappedBy = "generalInformation", cascade = CascadeType.ALL, orphanRemoval = true)
    List<WellInformation> wellInformation = new ArrayList<>();

    public GeneralInformation(Long id, String projectName, String clientId, String address, String country, String projectCurrency, String preferredUnits, String clientName, String city, String postalCode, String geoPoliticalData, String restrictionDetails, List<WellInformation> wellInformation) {
        this.id = id;
        this.projectName = projectName;
        this.clientId = clientId;
        this.address = address;
        this.country = country;
        this.projectCurrency = projectCurrency;
        this.preferredUnits = preferredUnits;
        this.clientName = clientName;
        this.city = city;
        this.postalCode = postalCode;
        GeoPoliticalData = geoPoliticalData;
        this.restrictionDetails = restrictionDetails;
        this.wellInformation = wellInformation;
    }

    public Long getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getClientId() {
        return clientId;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getProjectCurrency() {
        return projectCurrency;
    }

    public String getPreferredUnits() {
        return preferredUnits;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getGeoPoliticalData() {
        return GeoPoliticalData;
    }

    public String getRestrictionDetails() {
        return restrictionDetails;
    }

    public List<WellInformation> getWellInformation() {
        return wellInformation;
    }

    public void updateDetails(GeneralInformation updatedInfo) {
        this.projectName = updatedInfo.getProjectName();
        this.clientId = updatedInfo.getClientId();
        this.address = updatedInfo.getAddress();
        this.country = updatedInfo.getCountry();
        this.projectCurrency = updatedInfo.getProjectCurrency();
        this.preferredUnits = updatedInfo.getPreferredUnits();
        this.clientName = updatedInfo.getClientName();
        this.city = updatedInfo.getCity();
        this.postalCode = updatedInfo.getPostalCode();
        this.GeoPoliticalData = updatedInfo.getGeoPoliticalData();
        this.restrictionDetails = updatedInfo.getRestrictionDetails();
    }
    }





