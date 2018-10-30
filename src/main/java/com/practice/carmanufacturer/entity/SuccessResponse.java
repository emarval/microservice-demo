package com.practice.carmanufacturer.entity;

import org.springframework.http.HttpStatus;

public class SuccessResponse<T> extends Response {


    public SuccessResponse(T data, HttpStatus httpCode) {
        this.setData(data);
        this.setHttpCode(httpCode);
    }
}
