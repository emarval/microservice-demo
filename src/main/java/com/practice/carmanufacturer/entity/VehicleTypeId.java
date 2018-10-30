package com.practice.carmanufacturer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import java.io.Serializable;

public class VehicleTypeId implements Serializable {

//    @Id
//    @JsonProperty("Name")
    private String name;

//    @Id
//    @JsonProperty("IsPrimary")
    private Boolean isPrimary;
}
