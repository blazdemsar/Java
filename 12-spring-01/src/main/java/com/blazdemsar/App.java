package com.blazdemsar;

import com.blazdemsar.domain.Employee;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blazdemsar.domain.Address;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Legacy way of creating the Employee object and providing ");
    	Employee e = new Employee(1, "Blaz", "Programmer", 5000);
        Address a = new Address("addrLine1", "addrLine2", "newCity", "NEW York");
        e.setAddress(a);
        System.out.println("e: " + e);
        
        //Using Spring
        
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Employee e1 = (Employee)context.getBean("employee1");
        Employee e2 = (Employee)context.getBean("employee2");
        //System.out.println("e1: " + e1);
        //System.out.println("e2: " + e2);
        
        //OR
        
        System.out.println("e1: " + context.getBean("employee1"));
        System.out.println("e2: " + context.getBean("employee2"));
        
    }
}
