package com.training.SpringBootRESTRepo;

import com.training.SpringBootRESTRepo.entity.Book;
import com.training.SpringBootRESTRepo.repo.BookRepo;
import com.training.SpringBootRESTRepo.service.BookService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootRestRepoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
		SpringApplication.run(SpringBootRestRepoApplication.class, args);
		/**
		 * custom objects : s1, b1, getData1
		 * spring context: bookService, getData
		 */
		String s1 = new String("hello");
		BookService b1 = new BookService();
//		for(String beanname:context.getBeanDefinitionNames()){
//			System.out.println(beanname);
//		}
	}

	@Autowired
	private BookRepo bookRepo;
	@PostConstruct
	public void initialize(){
		Book b1 = new Book("Core Java","Hortsmann","java 8 features added", 290);
		Book b2 = new Book("HTML","Kelly","html 5 features and semantics", 199);
		Book b3 = new Book("C++","Jr. Arora","learn cpp oops concepts", 350);
		Book b4 = new Book("Python","Kelly","enjoy simple language python", 300);
		bookRepo.saveAll(Arrays.asList(b1, b2, b3, b4));
	}

	@Bean
	public Integer getData(){
		System.out.println("get data with Bean");
		int x =10;
		return  x;
	}

	public Integer getData1(){
		System.out.println("get data without Bean");
		int x =10;
		return  x;
	}

}
