package com.training.SpringBootRESTRepo.service;

import com.training.SpringBootRESTRepo.dto.BookDTO;
import com.training.SpringBootRESTRepo.entity.Book;
import com.training.SpringBootRESTRepo.repo.BookRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceRepo {

    private BookRepo bookRepo;

    public BookServiceRepo(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }
    public long getBooksCount(){
        return this.bookRepo.count();
    }
    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public List<BookDTO> getAllBooksV2(){

         return bookRepo.findAll().stream()
                 .map(book ->{
                     BookDTO bookDTO = new BookDTO();
                     bookDTO.setAuthor(book.getAuthor());
                     bookDTO.setPrice(book.getPrice());
                     bookDTO.setBookid(book.getBookid());
                     bookDTO.setTitle(book.getTitle());
                     return bookDTO;
                 }).collect(Collectors.toList());
    }
    public Book addNewBook(Book book){
        if(bookRepo.existsById(book.getBookid()))
            throw new EntityExistsException("Cannot add "+book.getBookid()+" already exists");
        return bookRepo.save(book);
    }
    public Book updateBook(Book book){
        if(!bookRepo.existsById(book.getBookid()))
            throw new EntityNotFoundException("cannot update "+book.getBookid()+" does not exist");
        return bookRepo.save(book);
    }
    public boolean deleteBook(int id){
        if(!bookRepo.existsById(id)) throw new EntityNotFoundException("cannot delete "+id+" does not exist");
        bookRepo.deleteById(id);
        return true;
    }
    public List<Book> getBooksByAuthor(String author){
        return this.bookRepo.findAllByAuthor(author);
    }

    public Book getBookById(int id){

        return bookRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException(id+" not found"));

//        if(!bookRepo.existsById(id))
//            throw new EntityNotFoundException(id+" not found");
//        return bookRepo.findById(id).get();
    }
}
