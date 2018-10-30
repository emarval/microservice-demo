package com.practice.carmanufacturer.repository;

import com.practice.carmanufacturer.entity.VehicleType;
import com.practice.carmanufacturer.entity.VehicleTypeId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTypeRepository extends CrudRepository<VehicleType, VehicleTypeId> {

    public List<VehicleType> findByName(String name);
}
