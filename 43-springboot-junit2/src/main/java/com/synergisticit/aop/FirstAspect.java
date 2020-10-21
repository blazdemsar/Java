package com.synergisticit.aop;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.synergisticit.domain.Employee;

@Aspect // @Aspect makes the class "FirstAspect" an Aspect. The aspect has advices,pointcut-expressions etc.
@Component
//@Order(value=1)
@Order(value=100)
public class FirstAspect {
	
	@After(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))")
	public void after(JoinPoint joinPoint) {
		System.out.println("FirstAspect @@@After:" + joinPoint.getSignature()); // prints the signature of the method
		System.out.println("Modifier:" + joinPoint.getSignature().getModifiers());
		System.out.println(", joinPoint.getDeclaringTypeName(): "+joinPoint.getSignature().getDeclaringTypeName());
		System.out.println(", joinPoint.DeclaringType(): "+joinPoint.getSignature().getDeclaringType());
		
		System.out.println(", joinPoint.getName(): "+joinPoint.getSignature().getName());
	}
	
	
	@Before(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))")
	public void before(JoinPoint joinPoint) {
		System.out.println("@@@ @Before:" + joinPoint.getSignature()); // prints the signature of the method
		
	}
	
	@AfterReturning(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))", returning = "xyzResult")
	public void afterReturning(JoinPoint joinPoint,  List<Employee> xyzResult ) {
		System.out.println("***FirstAspect @@@ @AfterReturning:" + joinPoint.getSignature()); // prints the signature of the method
		
		System.out.println("result: "+ xyzResult);
		if(xyzResult != null) {
			for(Employee e : xyzResult) {
			   System.out.println(e.getName().toUpperCase() + ": "+ "Salary: "+ e.getSalary() + ", incremented Salary: "+(e.getSalary() + e.getSalary()/3));
			}
		}
		
	}
	
	@Around(value="execution(* com.synergisticit.service.EmployeeService*.*(..))")
	public Object calculateTime(ProceedingJoinPoint pjp) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		StringBuffer stringBuffer = new StringBuffer();
		try {
			Object returnObject = pjp.proceed();
			stopWatch.stop();
			stringBuffer.append(pjp.getTarget().getClass().getName());
			stringBuffer.append(".");
			stringBuffer.append(pjp.getSignature().getName()+ "()");
			
			stringBuffer.append("@Around*** Execution Time: "+stopWatch.getTotalTimeSeconds() + " seconds.");
			
			System.out.println(stringBuffer);
			return returnObject;
						
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}			
	}
	
	//@AfterThrowing advice......
	@AfterThrowing(value="execution(* com.synergisticit.service.EmployeeService*.*(..))", throwing="e")
	public void afterThrowing(JoinPoint jp, Throwable e) {
		System.out.println("@AfterThrowing...."+ jp.getSignature());
		System.out.println("e: "+ e.getClass().getSimpleName());
		
	}

}
