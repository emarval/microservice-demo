package com.practice.carmanufacturer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity @IdClass(VehicleTypeId.class)

public class VehicleType {

    @Id
    @JsonProperty("Name")
    private String name;

    @Id
    @JsonProperty("IsPrimary")
    private Boolean isPrimary;

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }

    private String another;


    @ManyToMany(mappedBy = "vehicleTypes",
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE},
            fetch = FetchType.LAZY)
    List<CarManufacturer> carManufacturers;

    public VehicleType() {
    }

    public VehicleType(String name, Boolean isPrimary) {
        this.name = name;
        this.isPrimary = isPrimary;
    }
    public VehicleType(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleType)) return false;
        VehicleType that = (VehicleType) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(isPrimary, that.isPrimary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), isPrimary);
    }
}
