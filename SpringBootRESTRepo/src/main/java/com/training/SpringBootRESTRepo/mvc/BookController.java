package com.training.SpringBootRESTRepo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// MVC
@Controller
public class BookController {

    public BookController() {
        System.out.println("Book controller constructor");
    }
    // http://localhost:8080/greet
    @RequestMapping("/greet1")
    public String greetings(){
        System.out.println("greetings");
        return "hello";// name of the view : jsp or velocity
    }
}
