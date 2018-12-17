package com.practice.carmanufacturer.service;

import com.practice.carmanufacturer.Exceptions.CarManufacturerNotFound;
import com.practice.carmanufacturer.entity.*;
import com.practice.carmanufacturer.repository.CarManufacturerRepository;
import com.practice.carmanufacturer.repository.VehicleTypeRepository;
import com.practice.carmanufacturer.utils.SortByUtil;
import com.practice.carmanufacturer.utils.SearchParameters;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarManService {

    @Autowired
    private CarManufacturerRepository carManRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

//    @PersistenceContext(type = PersistenceContextType.EXTENDED)
//    EntityManager em;

    public Response<CarManufacturer> addManufacturer(CarManufacturer manufacturer){

        System.out.println("<======================================================================>");
        System.out.println("<======================================================================>");
        System.out.println("manufacturer to check " + manufacturer);
        System.out.println("<======================================================================>");
        System.out.println("<======================================================================>");

        /////this will make it work
        for (VehicleType vType : manufacturer.getVehicleTypes()) {

            Optional<VehicleType> vTypeInDb = vehicleTypeRepository.findByNameAndIsPrimary(vType.getName(),vType.getPrimary());
            if(vTypeInDb.isPresent()){
                System.out.println("is in the db");
                vType.setId(vTypeInDb.get().getId());
            }
//
        }
        try{
            if(manufacturer.getId() == 1044){
                System.out.println("este es");
            }
            System.out.println("<======================================================================>");
            System.out.println("manufacturer to save " + manufacturer);
            System.out.println("<======================================================================>");
            carManRepository.save(manufacturer);


            return new SuccessResponse<>(manufacturer, HttpStatus.CREATED);

        }catch (Exception ex){
            new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return null;
    }

    public Response<List<CarManufacturer>> initDb(List<CarManufacturer> manufacturerList){

        for (CarManufacturer manufacturer : manufacturerList) {
//            for (CarManufacturer carman : manufacturerList) {
//
//
//            }
            if(manufacturer.getId()==1054){
                System.out.println("I love you");
            }
            addManufacturer(manufacturer);
        }

//        carManRepository.saveAll(manufacturerList);
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
