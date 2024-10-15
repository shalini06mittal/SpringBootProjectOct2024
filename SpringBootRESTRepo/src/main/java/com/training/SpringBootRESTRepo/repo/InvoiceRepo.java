package com.training.SpringBootRESTRepo.repo;

import com.training.SpringBootRESTRepo.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice,Integer> {
    public List<Invoice> findAllByCustomerCustid(int id);
}
