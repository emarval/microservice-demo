package com.practice.carmanufacturer.utils;

import com.practice.carmanufacturer.entity.CarManufacturer;
import com.practice.carmanufacturer.entity.VehicleType;

import java.util.*;

public class SearchParameters {

    public final static String SEARCH_PARAM_COUNTRY = "COUNTRY";
    public final static String SEARCH_PARAM_NAME = "NAME";
    public final static String SEARCH_PARAM_VEHICLE_TYPE = "VEHICLE_TYPE.NAME";


    String country;
    String name;
    String vehicleType;

//    public Map<String,String> getSearchParams(){
//        Map<String, String> searchMap = new HashMap<>();
//        if(country!=null){
//            searchMap.put(SEARCH_PARAM_COUNTRY,country);
//        }
//        if (name != null){
//            searchMap.put(SEARCH_PARAM_NAME,name);
//        }
//        if(vehicleType!=null){
//            searchMap.put(SEARCH_PARAM_VEHICLE_TYPE,vehicleType);
//        }
//
//        return searchMap;
//    }

    public CarManufacturer getSearchParams(){
        CarManufacturer searchableManufacturer = new CarManufacturer();
        if(country!=null){
            searchableManufacturer.setCountry(country);
        }
        if (name != null){
            searchableManufacturer.setCommonName(name);
        }
        if(vehicleType!=null){

            searchableManufacturer.setVehicleTypes(Collections.singletonList(new VehicleType(vehicleType)));
        }

        return searchableManufacturer;
    }

    public Boolean hasParameters(){
        CarManufacturer emptyCarManufacturer = new CarManufacturer();

        return !emptyCarManufacturer.equals(getSearchParams());
    }

    public SearchParameters() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

}
