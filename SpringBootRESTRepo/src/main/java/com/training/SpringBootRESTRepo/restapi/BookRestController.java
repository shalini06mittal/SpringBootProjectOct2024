package com.training.SpringBootRESTRepo.restapi;

import com.training.SpringBootRESTRepo.entity.Book;
import com.training.SpringBootRESTRepo.entity.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API
 * Http methods: get post put delete patch
 * http headers:
 * http status code:
 */
@RestController // @Controller + @ResponseBody
/**
 * /books
 * CRUD
 */
@RequestMapping("/books")
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

    // GET
    @GetMapping
    public List<Book> getBooks(){

        return this.bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBoookById(@PathVariable Integer id){
        System.out.println("Id "+id);
        return this.bookService.getBookById(id);
    }
//    @GetMapping("/authors")
//    public List<Book> getAuthors(){
//
//        return this.bookService.getAllBooks();
//    }
    //POST
    @PostMapping
    public Book addBook(){
        return new Book();
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
