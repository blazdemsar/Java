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

import com.blazdemsar.dao.EmployeeDao;
import com.blazdemsar.dao.EmployeeDaoImplJdbcTemplate;
import com.blazdemsar.service.EmployeeService;
import com.blazdemsar.service.EmployeeServiceImplJdbcTemplate;

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
	
	@Bean(name="EmployeeDaoImplJdbcTemplate")
	public EmployeeDao employeeDao() {
		EmployeeDaoImplJdbcTemplate employeeDao = new EmployeeDaoImplJdbcTemplate();
		return employeeDao;
	}
	
	@Bean(name="EmployeeServiceImplJdbcTemplate")
	public EmployeeService employeeService() {
		EmployeeServiceImplJdbcTemplate employeeService = new EmployeeServiceImplJdbcTemplate();
		
		return employeeService;
	}
	
}
