package com.blazdemsar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blazdemsar.config.AppConfig;

public class App
{	
	/* Scope of a Bean :
	 * Non-web applications :
	 * 		1) Singleton : same object is used to serve all the requests for the bean
	 * 		2) Prototype : a unique object is used to serve each request for the bean
	 * 					   So if we request the same bean 10 times, it will create 10
	 * 					   unique objects
	 * 
	 * Web applications :
	 * 		3) Request : corresponds to the request scope of the servlet
	 * 		4) Session : 
	 * 		5) Global session scope : is in Portlet application
	 * 		6) Custom scope : uses scope interface. Mostly not required, used by 
	 * 					      Spring Framework developers
	 */
    public static void main( String[] args )
    {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        String[] beans = context.getBeanDefinitionNames();
        
        System.out.println("Printing names of all " + context.getBeanDefinitionCount() + " beans in context...");
        for (String bean : beans) {
        	System.out.println(bean);
        }
        
        // To test the scope go to Employee.java and comment out toString() method.
		/*
		 * System.out.println("Bean e1 is in Singleton scope......");
		 * System.out.println("Bean e1: " + context.getBean("e1"));
		 * System.out.println("Bean e1: " + context.getBean("e1"));
		 * System.out.println("Bean e1: " + context.getBean("e1"));
		 * System.out.println("Bean e2 is in Prototype scope......");
		 * System.out.println("Bean e2: " + context.getBean("e2"));
		 * System.out.println("Bean e2: " + context.getBean("e2"));
		 * System.out.println("Bean e2: " + context.getBean("e2"));
		 */
        
        context.close();
    }
}
