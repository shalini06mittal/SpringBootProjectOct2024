package com.training.SpringBootRESTRepo;

import com.training.SpringBootRESTRepo.constants.AppConstants;
import com.training.SpringBootRESTRepo.constants.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class MyGlobalExceptionHandler1 {

    public MyGlobalExceptionHandler1() {
        System.out.println("global exception handler 1");
    }
    @ExceptionHandler({Exception.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleException(Exception ex){
        Map<String, Object> map = new HashMap<>();
        map.put(AppConstants.STATUS, Status.FAILURE);

        if(ex instanceof MethodArgumentNotValidException){
            String msg = ((MethodArgumentNotValidException) ex).getAllErrors()
                    .stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            map.put("error",msg);
            return ResponseEntity.badRequest().body(map);
        }
        System.out.println("general exception");
        System.out.println(ex.getMessage());
        map.put("error",ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
}
