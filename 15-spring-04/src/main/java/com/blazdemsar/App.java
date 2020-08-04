package com.blazdemsar;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.blazdemsar.config.AppConfig;
import com.blazdemsar.domain.Employee;
import com.blazdemsar.service.EmployeeService;

public class App 
{
    public static void main( String[] args )
    {
        
    	// ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        String[] beans = context.getBeanDefinitionNames();
        for (String bean : beans) {
        	System.out.println(bean);
        }
        
        EmployeeService employeeService = (EmployeeService)context.getBean("employeeService");
        List<Employee> allEmployees = employeeService.findAll();
        
        for(Employee employee : allEmployees) {
        	System.out.println(employee);
        }
    }
}
