package com.training.SpringBootRESTRepo.repo;

import com.training.SpringBootRESTRepo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {
        // select * from tname where author=?
    public List<Book> findAllByAuthor(String author);
}
