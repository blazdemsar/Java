package com.synergisticit.aop;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.Employee;

@Aspect // @Aspect makes the class "FirstAspect" an Aspect. The aspect has advices,pointcut-expressions etc.
@Component
@Order(value=2) // Higher the Order value higher is the priority of the Aspect. ( Aspect will get applied earlier.)
public class SecondAspect {
	
	@AfterReturning(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))", returning = "xyzResult")
	public void afterReturning(JoinPoint joinPoint,  List<Employee> xyzResult ) {
		System.out.println("***SecondAspect @@@ @AfterReturning:" + joinPoint.getSignature()); // prints the signature of the method
		
		System.out.println("result: "+ xyzResult);
		if(xyzResult != null) {
			for(Employee e : xyzResult) {
			   System.out.println(e.getName().toUpperCase() + ": "+ "Salary: "+ e.getSalary() + ", incremented Salary: "+(e.getSalary() + e.getSalary()/10));
			}
		}
		
	}
}
