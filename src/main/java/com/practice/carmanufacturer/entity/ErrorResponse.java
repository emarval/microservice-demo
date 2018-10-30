package com.practice.carmanufacturer.entity;

import org.springframework.http.HttpStatus;

public class ErrorResponse extends Response {


    public ErrorResponse(String statusMessage, HttpStatus httpCode) {
        this.setStatusMessage(statusMessage);
        this.setHttpCode(httpCode);
        this.setStatusCode(httpCode.value());
    }
}
