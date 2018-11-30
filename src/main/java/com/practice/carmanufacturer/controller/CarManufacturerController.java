package com.practice.carmanufacturer.controller;

import com.practice.carmanufacturer.entity.CarManufacturer;
import com.practice.carmanufacturer.entity.Response;
import com.practice.carmanufacturer.entity.SuccessResponse;
import com.practice.carmanufacturer.service.CarManService;
import com.practice.carmanufacturer.utils.SearchParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CarManufacturerController {

    @Autowired
    CarManService carManService;

    @GetMapping(path = "/manufacturers")
    public ResponseEntity getAllManufacturers(@RequestParam(required = false) String sortBy
                                             ){

        Response<List<CarManufacturer>> successResponse = new SuccessResponse<>(carManService.findBy(new SearchParameters(),sortBy),HttpStatus.OK);


//        return new ResponseEntity(carManService.findBy(new SearchParameters(),sortBy),HttpStatus.OK);
        return new ResponseEntity(successResponse,successResponse.getHttpCode());
    }

    @GetMapping(path = "/manufacturers/search")
    public ResponseEntity searchManufacturer(/*@RequestParam(required = false) Boolean orderByCountry,
                                             @RequestParam(required = false) Boolean orderByName,
                                             @RequestParam(defaultValue = "ALL") String country,
                                             @RequestParam(defaultValue = "ALL",required = false) String name,
                                             @RequestParam(defaultValue = "ALL",required = false) String vehicleType
                                            */
                                                @RequestParam(required = false) String sortBy,
                                                @ModelAttribute SearchParameters paramaters){

        return new ResponseEntity(carManService.findBy(paramaters,sortBy),HttpStatus.OK);

//        return new ResponseEntity(SortByUtil.sortBy(carManService.findByCountry(paramaters.getCountry()),sortBy), HttpStatus.OK);


//        return new ResponseEntity(carManService.findByCountry(country), HttpStatus.OK);
//        return new ResponseEntity(carManService.findByVehicleType(vehicleType), HttpStatus.OK);
//        return new ResponseEntity(carManService.findByCountryAndCommonName(country,name), HttpStatus.OK);
    }

    @PostMapping(path = {"/manufacturers","/manfacturers/"})
    public void addManufacturer(@Valid @ModelAttribute CarManufacturer manufacturer){
        carManService.addManufacturer(manufacturer);
    }

    @DeleteMapping(path = {"/manufacturers/{id}","/manufacturers/{id}/"})
    public void removeManufacturer(@PathVariable String id){
        try {
            carManService.removeManufacturer(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PutMapping(path = {"/manufacturers/{id}","/manufacturers/{id}/"})
    public void updateManufacturer(@RequestBody CarManufacturer manufacturer, @PathVariable String id){
        carManService.updateManufacturer(manufacturer, id);
    }

}
