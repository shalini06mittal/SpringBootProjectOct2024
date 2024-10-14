package com.training.SpringBootRESTRepo;

import com.training.SpringBootRESTRepo.constants.AppConstants;
import com.training.SpringBootRESTRepo.constants.Status;
import com.training.SpringBootRESTRepo.exception.BookNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyGlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleRuntimeException(BookNotFoundException ex){
        Map<String, Object> map = new HashMap<>();
        map.put(AppConstants.STATUS, Status.FAILURE);
        map.put("error",ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        Map<String, Object> map = new HashMap<>();
        map.put(AppConstants.STATUS, Status.FAILURE);
        map.put("error",ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
}
