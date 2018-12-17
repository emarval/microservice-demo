package com.practice.carmanufacturer.repository;

import com.practice.carmanufacturer.entity.VehicleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleTypeRepository extends CrudRepository<VehicleType, Integer> {

    Optional<VehicleType> findByNameAndIsPrimary(String name, Boolean isPrimary);
}
