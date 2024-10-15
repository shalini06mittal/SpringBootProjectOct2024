package com.example.demo;

import java.io.FileInputStream;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.example.demo.entity.Customer;
import com.example.demo.listener.CustomerJobExecutionListener;
import com.example.demo.processor.CustomerProcessor;
import com.example.demo.repo.CustomerRepository;

import lombok.AllArgsConstructor;
//
//@Configuration
//@EnableBatchProcessing
//@AllArgsConstructor
public class SpringBatchConfig {

	private JobBuilderFactory jobBuilderFactory;
	
	private StepBuilderFactory stepBuilderFactory;
	
	private CustomerRepository customerRepository;
	
	private CustomerJobExecutionListener listener;
	@Bean
	public FlatFileItemReader<Customer> reader()
	{
		FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
		itemReader.setName("csvReader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}
	
	public LineMapper<Customer> lineMapper()
	{
		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setStrict(false);
		tokenizer.setNames("id","firstname","lastname","email","gender","contactno","country","dob");
		
		BeanWrapperFieldSetMapper<Customer> bMapper = new BeanWrapperFieldSetMapper<>();
		bMapper.setTargetType(Customer.class);
		
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(bMapper);
		return lineMapper;
	}
	@Bean
	public CustomerProcessor processor()
	{
		return new CustomerProcessor();
	}
	@Bean
	public RepositoryItemWriter<Customer> itemWriter()
	{
		RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();
		writer.setRepository(customerRepository);
		writer.setMethodName("save");
		return writer;
	}
	@Bean
	public Step step1()
	{
		return stepBuilderFactory.get("importcsv")
				.<Customer, Customer>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(itemWriter())
				.taskExecutor(executor())
				.build();
	}
	@Bean
	public Job createJob()
	{
		return jobBuilderFactory.get("saveCustomers")
				.listener(listener)
				.flow(step1())
				
				.end().build();
	}
	
	@Bean
	public AsyncTaskExecutor executor()
	{
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(10);
		return asyncTaskExecutor;
	}
	
}
