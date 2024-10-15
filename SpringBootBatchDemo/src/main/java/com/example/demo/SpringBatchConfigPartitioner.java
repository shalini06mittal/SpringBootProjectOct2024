package com.example.demo;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.demo.entity.Customer;
import com.example.demo.partition.RangePartition;
import com.example.demo.processor.CustomerProcessor;
import com.example.demo.writer.CustomerWriter;

import lombok.AllArgsConstructor;

//@Configuration
//@EnableBatchProcessing
//@AllArgsConstructor
public class SpringBatchConfigPartitioner {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;
    
    private CustomerWriter customerWriter;

    @Bean
    public FlatFileItemReader<Customer> reader() {
        FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
        itemReader.setName("csvReader");
        
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        
       
        return itemReader;
    }

    private LineMapper<Customer> lineMapper() {
        
        ClassPathResource resource = new ClassPathResource("customers.csv");
        Scanner scanner=null ;
        String line ="";
        try {
            scanner = new Scanner(resource.getInputStream());
            line = scanner.nextLine();
            System.out.println(line);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {scanner.close();}
       
        
        
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(line.split(","));

        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }

    @Bean
    public CustomerProcessor processor() {
        return new CustomerProcessor();
    }
    
    @Bean
    public RangePartition partition()
    {
    	return new RangePartition();
    }
    
    @Bean
    public PartitionHandler partitionHandler()
    {
        TaskExecutorPartitionHandler taskExecutorPartitionHandler = new TaskExecutorPartitionHandler();
        taskExecutorPartitionHandler.setGridSize(4);
        taskExecutorPartitionHandler.setTaskExecutor(taskExecutor());
        taskExecutorPartitionHandler.setStep(slaveStep());
        return taskExecutorPartitionHandler;
    }
   
    @Bean
    public Step masterStep() {
        return stepBuilderFactory.get("master-csv-step")
                .partitioner(slaveStep().getName(), partition())
                .partitionHandler(partitionHandler())
                .build();
    }
    @Bean
    public Step slaveStep() {
        return stepBuilderFactory.get("csv-step").<Customer, Customer>chunk(100)
                .reader(reader())
                .processor(processor())
                // when using partiioning
                .writer(customerWriter)
                .build();
    }
   
    
    
    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("importCustomers")
        		
                .flow(masterStep()).end().build();

    }

    @Bean
    public TaskExecutor taskExecutor() {
       ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
       executor.setMaxPoolSize(4);
       executor.setQueueCapacity(4);
       executor.setCorePoolSize(4);
       return executor;
    }

}
