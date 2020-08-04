package com.blazdemsar;

import com.blazdemsar.domain.Employee;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.blazdemsar.domain.Address;

public class App 
{
    public static void main( String[] args )
    {

        /* Using Spring
         * IoC : Inversion of Control : a design pattern where an application does NOT create the objects itself
    	 *       but it outsources the responsibility of creating the objects to third party that uses
    	 *		 Factory Pattern to create the objects
    	 *
    	 * DI : Dependency Injection : is a process of providing the dependent objects.
    	 * 		There are 3 types of DIs in Spring Framework
    	 * 		1) Constructor based DI
    	 * 		2) Setter based DI
    	 * 		3) Field based DI
    	 * 
    	 * Container/Spring container/IoC container : refers to ApplicationContext or BeanFactory or their
    	 * 		implementations.
    	 * 		-  IoC container is responsible for managing the life cycle of the beans.
    	 * 		   Beans are objects that are managed by the container
    	*/		
        
    	
    	// Autowiring/Bean Autowiring : is a process of providing the collaborating bean by Spring
    	// Container. It is achieved by providing the autowire attribute in <bean> tag. The autowire
    	// attribute can have any of these values: 1) byType, 2) byName, 3)constructor, 4)default, 5)no,
    	// "default" and "no"
    	// Autowiring in Spring is achieved by "autowire" attribute in <bean> tag xml configuration file
    	// or by @Autowired annotation in Java config. file
        
    	//ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml"); // AbstractApplicationContext defines .close() method
    	
    	Employee e1 = (Employee)context.getBean("employee1");
        Employee e2 = (Employee)context.getBean("employee2");
        //System.out.println("e1: " + e1);
        //System.out.println("e2: " + e2);
        
        //OR
        
        System.out.println("e1: " + context.getBean("employee1"));
        System.out.println("e2: " + context.getBean("employee2"));
        
        
        System.out.println("Printing the names of all " + context.getBeanDefinitionCount() + " beans in the container...");
        String [] beanNames = context.getBeanDefinitionNames();
        
        for(String beanName : beanNames) {
        	System.out.println(beanName);
        }
        
        // Using BeanFactory
        System.out.println("================================= Using BeanFactory ======================");
        
        // Resource resource = new ClassPathResource("beans.xml");
        //     OR
        //	   |
        //     V
        // Resource resource = new FileSystemResource("src/main/java/beans.xml");
        //     OR
        //     |
        //     V
        Resource resource = new FileSystemResource("C:\\Users\\blazd\\JSP_Servlet\\13-spring-02\\src\\main\\java\\beans.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        //     OR
        //     |
        //     V
        // BeanFactory factory = (BeanFactory)context;
        //     OR
        // BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
        Employee e4 = (Employee)factory.getBean("employee4");
        System.out.println("e4: " + e4);
        System.out.println("Going to call the close() method of the context....");
        context.close();
        System.out.println("Context has been closed.");
    }
}
