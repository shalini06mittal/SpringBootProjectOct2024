package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootBatchDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBatchDemoApplication.class, args);
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
	@Bean
	public void data()
	{
		System.out.println("**************************************\n\n");
		System.out.println(System.getProperty("name"));
	}

}
