package com.blazdemsar.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.blazdemsar.domain.Employee;

@Configuration   //makes the class AppConfig.java a configuration file that provides the beans
@ImportResource("classpath:beans.xml")
public class AppConfig {
	
	@Bean // @Bean is applied on methods that provide a type for the bean, that are in java class annotated with @Configuration
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		
		return messageSource;
	}
	
	@Bean(name="employee11")
	public Employee employee1() {
		return new Employee(1, "Blaz", "DBA", 8000);
	}
	
	@Bean(name="employee22")
	public Employee employee2() {
		return new Employee(2, "Domen", "Programmer", 6500);
	}
	
	@Bean(name="employee33")
	public Employee employee3() {
		return new Employee(3, "Natalija", "MechE", 10000);
	}
}
