package com.training.SpringBootRESTRepo.restapi;

import com.training.SpringBootRESTRepo.constants.AppConstants;
import com.training.SpringBootRESTRepo.constants.Status;
import com.training.SpringBootRESTRepo.entity.Book;
import com.training.SpringBootRESTRepo.service.BookService;
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

    // http://localhost:8080/books?author=
    // GET
    @GetMapping(produces = {"application/json","application/xml"})
    public List<Book> getBooks (@RequestParam(required = false, defaultValue = "Kelly") String author){
        if(author.equalsIgnoreCase("all"))
            return this.bookService.getAllBooks();
        return  this.bookService.getBooksByAuthor(author);
    }

    @PostMapping(consumes = {"application/json","application/xml"},
            produces = {"application/json","application/xml"})
    public ResponseEntity<Object> addBook(@RequestBody Book book){
        System.out.println("Book "+book);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(AppConstants.STATUS, Status.SUCCESS);
            map.put("book",bookService.addNewBook(book) );
            return ResponseEntity.ok(map);
        } catch (RuntimeException e){
        map.put(AppConstants.STATUS, Status.FAILURE);
        map.put("error",e.getMessage());
        return ResponseEntity.badRequest().body(map);
        }
    }
    @PutMapping
    public ResponseEntity<Object> updateBook(@RequestBody Book book) {
        System.out.println("Book " + book);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(AppConstants.STATUS, Status.SUCCESS);
            map.put("book", bookService.updateBook(book));
            return ResponseEntity.ok(map);
        } catch (RuntimeException e) {
            map.put(AppConstants.STATUS, Status.FAILURE);
            map.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id){
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(AppConstants.STATUS, Status.SUCCESS);
            if(bookService.deleteBook(id)) {
                map.put("message", "Book deleted successfully");
                return ResponseEntity.ok(map); }
        } catch (RuntimeException e){
            map.put(AppConstants.STATUS, Status.FAILURE);
            map.put("error",e.getMessage());
        }
        return ResponseEntity.badRequest().body(map);
    }
//    @GetMapping("/author/{name}")
//    public List<Book> getBooks(@PathVariable String name){
//
//        return this.bookService.getBooksByAuthor(name);
//    }

//    @GetMapping("/{id}")
//    public Book getBoookById(@PathVariable Integer id){
//        System.out.println("Id "+id);
//        try {
//            return this.bookService.getBookById(id);
//        }catch(RuntimeException e){
//            return null;
//        }
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBoookById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(AppConstants.STATUS, Status.SUCCESS);
            map.put("book", bookService.getBookById(id));
            return ResponseEntity.ok(map);
        }
        catch (RuntimeException e){
            map.put(AppConstants.STATUS, Status.FAILURE);
            map.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }
//    @GetMapping("/authors")
//    public List<Book> getAuthors(){
//
//        return this.bookService.getAllBooks();
//    }
    //POST
//    @PostMapping
//    public Book addBook(){
//        return new Book();
//    }

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
