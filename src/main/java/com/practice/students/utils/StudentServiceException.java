package com.practice.students.utils;

import com.practice.carmanufacturer.entity.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class StudentServiceException implements ErrorController{
//
    private static final String PATH = "/error";
//    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ErrorResponse> responseForError(Exception ex){
//
////        if(LOGGER.isDebugEnabled()){
//            LOGGER.debug("Cause: ",ex.getMessage());
//            LOGGER.debug("StackTrace",ex.getStackTrace());
//
//            LOGGER.error("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
////        }
//
//        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
//
//        return new ResponseEntity<>(error,error.getStatusCode());
//    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String studentErroHandler(Exception ex){
//
//        System.out.println("entre aqui");
//
//        return ex.getMessage();
//    }

//}
