package com.practice.carmanufacturer.utils;

import com.practice.carmanufacturer.entity.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends RuntimeException implements ErrorController {

    private static final String PATH = "/error";
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> responseForError(Exception ex){

        LOGGER.debug("debug msg");
        LOGGER.info("info msg");
        LOGGER.warn("warn msg");
        LOGGER.error("error msg");

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Cause: ",ex.getMessage());
            LOGGER.debug("StackTrace",ex.getStackTrace());
        }

        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error,error.getHttpCode());
    }



    @Override
    public String getErrorPath() {
        return PATH;
    }
}
