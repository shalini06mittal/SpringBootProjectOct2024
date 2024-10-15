package com.training.SpringBootRESTRepo;

import com.training.SpringBootRESTRepo.constants.AppConstants;
import com.training.SpringBootRESTRepo.constants.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyGlobalExceptionHandler1 {

    public MyGlobalExceptionHandler1() {
        System.out.println("global exception handler 1");
    }
    @ExceptionHandler({Exception.class, MethodValidationException.class})
    public ResponseEntity<Object> handleException(Exception ex){

        System.out.println("general exception");
        System.out.println(ex.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put(AppConstants.STATUS, Status.FAILURE);
        map.put("error",ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
}
