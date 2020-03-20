package com.example.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.example.bean.User;

@Configuration
@EnableBatchProcessing

public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Value("classPath:/input/UserData.csv")
	private Resource inputResource;

	@Autowired
	DataSource dataSource;
	
	@Autowired 
	EntityManagerFactory entityManager;

	@Bean
	public Job readCSVFileJob() {
		return jobBuilderFactory.get("readCSVFileJob").incrementer(new RunIdIncrementer()).start(step()).build();
	}

	@Bean
	public Step step() {
		return stepBuilderFactory.get("step").<User, User>chunk(3).reader(reader()).processor(processor())
				.writer(writer()).build();
	}

	@Bean
	public ItemProcessor<User, User> processor() {
		return new DBLogProcessor();
	}

	@Bean
	public FlatFileItemReader<User> reader() {
		FlatFileItemReader<User> itemReader = new FlatFileItemReader<User>();
		itemReader.setLineMapper(lineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(inputResource);
		return itemReader;
	}

	@Bean
	public LineMapper<User> lineMapper() {
		DefaultLineMapper<User> lineMapper = new DefaultLineMapper<User>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "userid", "username", "gender", "age", "pancard", "aadhar", "salary" });
		lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4, 5, 6 });
		BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<User>();
		fieldSetMapper.setTargetType(User.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	@Bean
	public JdbcBatchItemWriter<User> writer() {
		JdbcBatchItemWriter<User> itemWriter = new JdbcBatchItemWriter<User>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO User (username,gender,age,pancard,aadhar,salary) VALUES "
				+ "( :username, :gender,:age,:pancard,:aadhar,:salary)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
		return itemWriter;
	}
	
	
	
	@Bean
    public JpaItemWriter<User> jpawriter() {
        JpaItemWriter<User> userItemWriter = new JpaItemWriter<User>();
        userItemWriter.setEntityManagerFactory(entityManager);       
        return userItemWriter;
    }    
 

}
