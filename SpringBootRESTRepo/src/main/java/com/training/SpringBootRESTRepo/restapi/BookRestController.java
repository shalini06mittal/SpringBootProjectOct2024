package com.training.SpringBootRESTRepo.restapi;

import com.training.SpringBootRESTRepo.entity.Book;
import com.training.SpringBootRESTRepo.entity.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST API
 * Http methods: get post put delete patch
 * http headers:
 * http status code:
 */
@RestController // @Controller + @ResponseBody
public class BookRestController {
    // Field Injection
    //@Autowired
    private BookService bookService;

    //@Autowired
    public BookRestController(){//BookService bookService) {
        System.out.println("Book Rest Controller");
        //this.bookService = bookService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        System.out.println("set book service");
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public List<Book> getBooks(){
        return this.bookService.getAllBooks();
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
