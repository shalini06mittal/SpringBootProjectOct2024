package com.training.SpringBootRESTRepo.repo;

import com.training.SpringBootRESTRepo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {

}
