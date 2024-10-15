package com.training.SpringBootRESTRepo.restapi;


import com.training.SpringBootRESTRepo.entity.Customer;
import com.training.SpringBootRESTRepo.entity.FictionalCharacter;
import com.training.SpringBootRESTRepo.service.CharacterService;
import com.training.SpringBootRESTRepo.service.CustomerService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id){
           return customerService.getCustomerProfileAndInvoicesById(id);
    }
    @PostMapping()
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer){
        try{
            Customer ob = customerService.insertCustomer(customer);
            return ResponseEntity.ok(ob);
        }catch (EntityExistsException e){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorMap);
        }
    }

}
