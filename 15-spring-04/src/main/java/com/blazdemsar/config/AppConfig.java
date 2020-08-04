package com.blazdemsar.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionManager;

@EnableJpaRepositories(basePackages = {"com.blazdemsar.repository"})
@ComponentScan(basePackages = "com.blazdemsar.repository")
@Configuration
@ImportResource("classpath:beans.xml")
public class AppConfig {
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public TransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}
	
}
