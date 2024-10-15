package com.training.SpringBootRESTRepo;

import com.training.SpringBootRESTRepo.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

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
