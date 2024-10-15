package com.training.SpringBootRESTRepo.restapi;

import com.training.SpringBootRESTRepo.constants.AppConstants;
import com.training.SpringBootRESTRepo.constants.Status;
import com.training.SpringBootRESTRepo.dto.BookDTO;
import com.training.SpringBootRESTRepo.entity.Book;
import com.training.SpringBootRESTRepo.service.BookService;
import com.training.SpringBootRESTRepo.service.BookServiceRepo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST API
 * Http methods: get post put delete patch
 * http headers:
 * http status code:
 */
@RestController
@RequestMapping("/books/versions")
public class BookRestControllerWithVersioning {

    private BookServiceRepo bookService;

    public BookRestControllerWithVersioning(BookService bookService, BookServiceRepo bookServiceRepo) {
        System.out.println("Book Rest Controller");
        this.bookService = bookServiceRepo;
        //this.bookServiceRepo = bookServiceRepo;
    }

    /**
     * Versioning
     * 1. URL Path parameter
     * 2. Query param
     * 3. headers
     * 4. Accept header
     * @return
     */

//http://localhost:8081/books/ex/v1
    @GetMapping(path = "/v1",produces = {"application/json","application/xml"})
    public List<Book> getBooksPathParam1 (@RequestParam(required = false, defaultValue = "Kelly") String author){
        if(author.equalsIgnoreCase("all"))
            return this.bookService.getAllBooks();
        return  this.bookService.getBooksByAuthor(author);
    }
    //http://localhost:8081/books/ex/v2
    @GetMapping(path = "/v2",produces = {"application/json","application/xml"})
    public List<BookDTO> getBooksPathParam2 (){

            return this.bookService.getAllBooksV2();

    }

    //http://localhost:8081/books/ex?version=1
    @GetMapping(produces = {"application/json","application/xml"}, params = "version=1")
    public List<Book> getBooksQueryParam1 (@RequestParam(required = false, defaultValue = "Kelly") String author){
        if(author.equalsIgnoreCase("all"))
            return this.bookService.getAllBooks();
        return  this.bookService.getBooksByAuthor(author);
    }
    //http://localhost:8081/books/ex?version=2
    @GetMapping( params = "version=2",produces = {"application/json","application/xml"})
    public List<BookDTO> getBooksQueryParamV2 (){

        return this.bookService.getAllBooksV2();

    }

    //headers: version = 1
    @GetMapping(produces = {"application/json","application/xml"}, headers = "version=1")
    public List<Book> getBooksHeaderV1 (@RequestParam(required = false, defaultValue = "Kelly") String author){
        if(author.equalsIgnoreCase("all"))
            return this.bookService.getAllBooks();
        return  this.bookService.getBooksByAuthor(author);
    }
    //http://localhost:8081/books/ex?version=2
    @GetMapping( headers = "version=2",produces = {"application/json","application/xml"})
    public List<BookDTO> getBooksHeaderV2 (){

        return this.bookService.getAllBooksV2();

    }

    //Accept: application/app-v1+json
    @GetMapping(produces = {"application/app-v1+json"})
    public List<Book> getBooksAcceptHeaderV1 (@RequestParam(required = false, defaultValue = "Kelly") String author){
        if(author.equalsIgnoreCase("all"))
            return this.bookService.getAllBooks();
        return  this.bookService.getBooksByAuthor(author);
    }
    //http://localhost:8081/books/ex
    @GetMapping( produces = {"application/app-v2+json"})
    public List<BookDTO> getBooksAcceptHeaderV2 (){

        return this.bookService.getAllBooksV2();

    }

}
