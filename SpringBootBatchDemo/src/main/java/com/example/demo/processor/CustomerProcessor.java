package com.example.demo.processor;

import java.util.function.Function;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;

@Service
public class CustomerProcessor implements ItemProcessor<Customer, Customer>{

	@Override
	public Customer process(Customer item) throws Exception {
		
		System.out.println(item.getId());
		
		item.setFirstName(item.getFirstName().toUpperCase());
		return item;
	}
}
