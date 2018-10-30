package com.practice.carmanufacturer.repository;

import com.practice.carmanufacturer.entity.CarManufacturer;
import com.practice.carmanufacturer.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarManufacturerRepository extends CrudRepository<CarManufacturer, Integer>, QueryByExampleExecutor<CarManufacturer> {

    List<CarManufacturer> findByCountry(String country);

    List<CarManufacturer> findByCountryAndCommonName(String country, String commonName);

    List<CarManufacturer> findByVehicleTypesIn(List<VehicleType> vehicleTypes);

}
