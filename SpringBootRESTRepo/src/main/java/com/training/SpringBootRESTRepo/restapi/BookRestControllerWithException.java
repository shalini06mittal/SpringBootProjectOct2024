package com.training.SpringBootRESTRepo.restapi;

import com.training.SpringBootRESTRepo.constants.AppConstants;
import com.training.SpringBootRESTRepo.constants.Status;
import com.training.SpringBootRESTRepo.entity.Book;
import com.training.SpringBootRESTRepo.exception.BookNotFoundException;
import com.training.SpringBootRESTRepo.service.BookService;
import com.training.SpringBootRESTRepo.service.BookServiceRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/books/ex")
public class BookRestControllerWithException {

    //private BookService bookService;
    private BookServiceRepo bookService;

    public BookRestControllerWithException(BookService bookService, BookServiceRepo bookServiceRepo) {
        System.out.println("Book Rest Controller");
        this.bookService = bookServiceRepo;
        //this.bookServiceRepo = bookServiceRepo;
    }

    @GetMapping("/count")
    public long getBookCount(){
        return bookService.getBooksCount();
    }

    @GetMapping(produces = {"application/json","application/xml"})
    public List<Book> getBooks (@RequestParam(required = false, defaultValue = "Kelly") String author){
        if(author.equalsIgnoreCase("all"))
            return this.bookService.getAllBooks();
        return  this.bookService.getBooksByAuthor(author);
    }

    @ExceptionHandler(RuntimeException.class)
    public  ResponseEntity<Object> handleRuntimeException(RuntimeException ex){
        Map<String, Object> map = new HashMap<>();
        map.put(AppConstants.STATUS, Status.FAILURE);
        map.put("error",ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
    @PostMapping(consumes = {"application/json","application/xml"},
            produces = {"application/json","application/xml"})
    public ResponseEntity<Object> addBook( @RequestBody Book book){
        System.out.println("Book !! "+book);
        Map<String, Object> map = new HashMap<>();
            map.put(AppConstants.STATUS, Status.SUCCESS);
            map.put("book",bookService.addNewBook(book) );
            return ResponseEntity.ok(map);

    }
    @PutMapping
    public ResponseEntity<Object> updateBook(@RequestBody Book book) {
        System.out.println("Book " + book);
        Map<String, Object> map = new HashMap<>();

            map.put(AppConstants.STATUS, Status.SUCCESS);
            map.put("book", bookService.updateBook(book));
            return ResponseEntity.ok(map);

    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id){
        Map<String, Object> map = new HashMap<>();
        bookService.deleteBook(id);
                map.put(AppConstants.STATUS, Status.SUCCESS);
                map.put("message", "Book deleted successfully");
                return ResponseEntity.ok(map);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
            map.put(AppConstants.STATUS, Status.SUCCESS);
            map.put("book", bookService.getBookById(id));
            return ResponseEntity.ok(map);

    }

}
