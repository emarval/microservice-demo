package com.practice.carmanufacturer.service;

import com.practice.carmanufacturer.Exceptions.CarManufacturerNotFound;
import com.practice.carmanufacturer.entity.CarManufacturer;
import com.practice.carmanufacturer.entity.ErrorResponse;
import com.practice.carmanufacturer.entity.Response;
import com.practice.carmanufacturer.entity.SuccessResponse;
import com.practice.carmanufacturer.repository.CarManufacturerRepository;
import com.practice.carmanufacturer.utils.SortByUtil;
import com.practice.carmanufacturer.utils.SearchParameters;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarManService {

    @Autowired
    private CarManufacturerRepository carManRepository;

    public Response<CarManufacturer> addManufacturer(CarManufacturer manufacturer){


        try{
            carManRepository.save(manufacturer);

            return new SuccessResponse<>(manufacturer, HttpStatus.CREATED);

        }catch (Exception ex){
            new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return null;
    }

    public Response<List<CarManufacturer>> initDb(List<CarManufacturer> manufacturerList){

//        for (CarManufacturer manufacturer : manufacturerList) {
//            addManufacturer(manufacturer);
//        }

        carManRepository.saveAll(manufacturerList);
//        return new SuccessResponse<>(manufacturerList,HttpStatus.CREATED);
        return null;
    }

    public Response<CarManufacturer> removeManufacturer(String id) throws Exception{

        Response<CarManufacturer> response;

        try{

            if( (id != null) && (!id.isEmpty()) ){

                CarManufacturer manufacturerToRemove = carManRepository.findById(Integer.valueOf(id)).orElseThrow(() ->new CarManufacturerNotFound());

                carManRepository.deleteById(Integer.valueOf(id));

                response = new SuccessResponse<>(manufacturerToRemove, HttpStatus.OK);

            }
//            else throw new IllegalArgumentException("The id parameter must not be null or empty");

            else throw new NumberFormatException("The id parameter must not be null or empty");

        }catch (NumberFormatException e){
////CHANGEEEEEEEEEE THIS IS WROOOONG
            response  = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);

        }



        return response;
//        return null;
    }

    public Response<CarManufacturer> updateManufacturer(CarManufacturer manufacturer, String id) {
        carManRepository.save(manufacturer);

//        return new SuccessResponse<>(manufacturer, HttpStatus.OK);
        return null;
    }

//    public List<CarManufacturer> findByCountry(String country){
//       return carManRepository.findByCountry(country);
//    }
//
//    public List<CarManufacturer> findByCountryAndCommonName(String country, String commonName){
//        return carManRepository.findByCountryAndCommonName(country,commonName);
//    }
//
//    public List<CarManufacturer> findByVehicleType(String vehicleTypeName){
//        List<VehicleType> vehicleTypes = vehicleTypeRepository.findByName(vehicleTypeName);
//
//        return carManRepository.findByVehicleTypesIn(vehicleTypes);
//
//    }

    public List<CarManufacturer> findBy(SearchParameters searchParameters, String sortBy){


        List<CarManufacturer> carManufacturers = new ArrayList<>();


        if( (searchParameters!=null) && (searchParameters.hasParameters()) ){
            CarManufacturer searchableCarManufacturer = searchParameters.getSearchParams();
            carManRepository.findAll(Example.of(searchableCarManufacturer)).forEach(carManufacturers::add);
        }
        else{
            carManufacturers = this.findAll();
        }


        return SortByUtil.sortBy(carManufacturers,sortBy);
    }

    private List<CarManufacturer> findAll() {
        List<CarManufacturer> carManufacturers = new ArrayList<>();

        carManRepository.findAll().forEach(carManufacturers::add);

        return carManufacturers;
    }
}
