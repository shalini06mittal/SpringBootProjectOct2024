package com.training.SpringBootRESTRepo.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API
 * Http methods: get post put delete patch
 * http headers:
 * http status code:
 */
@RestController // @Controller + @ResponseBody
public class BookRestController {

    public BookRestController() {
        System.out.println("Book Rest Controller");
    }


    // http://localhost:8080/greet

    /**
     * Request Mapping : GET POST PUT DELETE
     * @return
     */
    @RequestMapping("/greet")
    public String greetings(){
        return "hello";
    }
}
