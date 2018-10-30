package com.practice.carmanufacturer.utils;


public enum SortByParams {

//    COUNTRY,
//    NAME,
    COUNTRY ("country"),
    NAME ("commonName");

    private String param;

    SortByParams(String param) {
        this.param = param;
    }

    public String getParam(){
        return this.param;
    }

    public static SortByParams fromString(String param){

        for(SortByParams enumParam: SortByParams.values()){
            if(enumParam.getParam().trim().equalsIgnoreCase(param)){
                return enumParam;
            }
        }
        return null;

    }

}
