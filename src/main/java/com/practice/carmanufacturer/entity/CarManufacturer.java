package com.practice.carmanufacturer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class CarManufacturer {

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Mfr_CommonName")
    private String commonName;

    @Id
    @JsonProperty("Mfr_ID")
    private Integer id;

    @JsonProperty("Mfr_Name")
    private String name;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE},
            fetch = FetchType.LAZY)
    @JoinTable
    @JsonProperty(value = "VehicleTypes")
    private List<VehicleType> vehicleTypes;

    public CarManufacturer() {
        this.vehicleTypes = new ArrayList<>();
    }

    public CarManufacturer(String country, String commonName, Integer id, String name, List<VehicleType> vehicleTypes) {
        super();
        this.country = country;
        this.commonName = commonName;
        this.id = id;
        this.name = name;
        this.vehicleTypes = vehicleTypes;
    }

    private String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    private Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }





    @Override
    public String toString() {
        return "CarManufacturer{" +
                "country='" + country + '\'' +
                ", commonName='" + commonName + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", vehicleTypes=" + vehicleTypes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarManufacturer)) return false;
        CarManufacturer that = (CarManufacturer) o;
        return Objects.equals(getCountry(), that.getCountry()) &&
                Objects.equals(getCommonName(), that.getCommonName()) &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getVehicleTypes(), that.getVehicleTypes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getCommonName(), getId(), getName(), getVehicleTypes());
    }
}
