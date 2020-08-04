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
import com.blazdemsar.domain.Address;
import com.blazdemsar.domain.Employee;
import com.blazdemsar.service.EmployeeService;
import com.blazdemsar.service.EmployeeServiceImplJdbcTemplate;

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
        //Employee employee = new Employee("David", "programmer", 5000, new Address("H. No. 100", "Dream Society", "Dream City", "Dream State"));      
        // employeeService.saveEmployee(employee);
        
        // employeeService.updateById(3, "Testing", 6767); // updates the employee object/record
        
        System.out.println("findById(4) : " + employeeService.findById(4));
        
        List<Employee> allEmployees = employeeService.findAll();
        
        for(Employee emp : allEmployees) {
        	System.out.println(emp);
        }
        
        employeeService.deleteById(7);
        employeeService.deleteById(13);
        
        //allEmployees.forEach(e -> System.out.println(e));
        
        EmployeeServiceImplJdbcTemplate employeeServiceImplJdbcTemplate = (EmployeeServiceImplJdbcTemplate)context.getBean("EmployeeServiceImplJdbcTemplate");
        //employeeServiceImplJdbcTemplate.saveEmployee(new Employee("Manny", "Ind. Eng.", 8989, new Address("H. No. 100", "Dream Society", "Dream City", "Dream State")));
        allEmployees.forEach(e -> System.out.println(e));
        
        System.out.println("\nfindAll() method uses jdbcTemplate to get the list of employees....");
        employeeServiceImplJdbcTemplate.findAll();
        
        employeeServiceImplJdbcTemplate.findById(3);
        employeeServiceImplJdbcTemplate.deleteById(12);
        employeeServiceImplJdbcTemplate.updateById(11, "Bestie", 1000);
        
        
    }
}
