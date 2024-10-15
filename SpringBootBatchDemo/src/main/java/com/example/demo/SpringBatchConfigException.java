package com.example.demo;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.example.demo.entity.User;

import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfigException {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<User> reader() {
        FlatFileItemReader<User> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/user.csv"));
        itemReader.setName("csvReader");
        
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        
       
        return itemReader;
    }

    private LineMapper<User> lineMapper() {
        
        ClassPathResource resource = new ClassPathResource("user.csv");
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
       
        
        
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(line.split(","));

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }

    @Bean
    public ItemProcessor<User, String> processor() {
        return new ItemProcessor<User, String>() {

            @Override
            @Nullable
            public String process(@NonNull User item) throws Exception {
                // TODO Auto-generated method stub
                System.out.println(item.getFirstName()+" "+item.getLastName());
                return item.getFirstName()+" "+item.getLastName();
            }
        };
    }

    @Bean
    public ItemWriter<String> writer() {
        return new ItemWriter<String>() {
            
            @Override
            public void write(List<? extends String> items) throws Exception {
                // TODO Auto-generated method stub
                System.out.println("writing data");
                System.out.println(items);
            }
        };
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-step").<User, String>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                
                .skipLimit(2)
                .skip(FlatFileParseException.class)
                .build();
    }
    
    
    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("importCustomers")
                .flow(step1())
                .end().build();

    }


}
