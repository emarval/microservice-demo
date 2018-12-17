package com.practice.carmanufacturer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.mapping.JpaPersistentEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
//@Table(indexes = {@Index(name = "INDEX_TEST", columnList = "name, isPrimary", unique = true)})

public class VehicleType {

//    @Id
//    @JsonIgnore
//    @Column(name = "name")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @Id
    @JsonProperty("Name")
    private String name;

//    @Id
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

    public String getName() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
