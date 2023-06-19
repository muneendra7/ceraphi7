package com.ceraphi.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClientDetailsDTO {

    @NotBlank(message = "Client name is required")
    private String clientName;
    @NotNull(message = "Client type is required")
    private String clientType;
    @NotEmpty(message = "fields Can't be empty")
    @Email(message = "Invalid email format")
    private String email;
    @NotEmpty(message = "fields Can't be empty")
    private String language;
    @NotEmpty(message = "fields Can't be empty")
    private String address;
    @NotEmpty(message = "fields Can't be empty")
    private String city;
    @NotEmpty(message = "fields Can't be empty")
    private String country;
    @NotEmpty(message = "fields Can't be empty")
    private String postalCode;
    @NotEmpty(message = "fields Can't be empty")
    private String localCurrency;
    private boolean restriction;
    @NotEmpty(message = "fields Can't be empty")
    private String geopoliticalData;
    @NotEmpty(message = "fields Can't be empty")
    private String restrictionDetails;
}