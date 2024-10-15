package com.example.demo.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repo.CustomerRepository;

@Service
public class CustomerWriter implements ItemWriter<Customer> {
    
    @Autowired
    private CustomerRepository repository;

    @Override
    public void write(List<? extends Customer> items) throws Exception {
       System.out.println("Thread "+Thread.currentThread().getName());
        repository.saveAll(items);
    }
    
    

}
