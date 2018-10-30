package com.practice.carmanufacturer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.practice.carmanufacturer.entity.CarManufacturer;
import com.practice.carmanufacturer.service.CarManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class CarManufacturerApplication {



    private static CarManService carManService;

    public CarManufacturerApplication(CarManService carManService) {

        CarManufacturerApplication.carManService = carManService;
    }


    public static void main(String[] args) {
        SpringApplication.run(CarManufacturerApplication.class, args);

        System.out.println("hola");

        carManService.addManufacturer(null);



    }
}
