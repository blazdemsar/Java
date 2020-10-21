package com.synergisticit;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.junit.runner.RunWith;
import org.junit.platform.runner.JUnitPlatform;
import org.springframework.boot.test.context.SpringBootTest;



import com.synergisticit.repository.UserRepository;
import com.synergisticit.service.UserService;

@RunWith(JUnitPlatform.class)
@SpringBootTest

class ApplicationTests {
  // JUnit is a unit testing framework for Java, created by Erich Gamma and Kent Beck. 
	// Unit Testing =>Integration Testing =>System Testing => Acceptance Testing 
	// Unit Testing is at the base of the pyramid of testing and has the highest test cases, as we go upside the pyramid test cases become less.
	/* Unit Testing is done by developers, test case can be written by them or some times they are also provided by client.
	   Integration Testing: Mostly done by testing team but can be also done by developers as per resource availability
	   System testing: full application is tested by dedictaed testing team
	   Acceptance Testing: is done by the client's testing team before they accept the final version of the project to be deployed on production server.
	   The client can also ask the testing at developer's site to test the application on their behalf.
	  TDD: Test Driven Development. test cases are written before the code. Based on testing framework. All callses in the application are tested. This makes quick and easy integration of modules.
	  More tests you write, less is the pressure while handling the application to the client.
	  More tests => More Accuracy=>More Reliability
	 */
	
	/* 24-Aug-2020
	JUnit is used to unit test the individual units(classes) and their methods.
	JUnit 5: junit-jupiter engine + junit-vintage engine + junit-platform
	Unit testing tools: JUnit,JMockito
	*/
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	
	@Test // @Test makes the method contextLoads() to run as a JUnit test 
	void contextLoads() {
		System.out.println("userRepository: "+ userRepository);
		//assertNull(userRepository, "UserRepository is not null."); // this test case passes when UserRepositoryis not autowired
		assertNotNull(userRepository, "UserRepository is null, you must autowire it.");
	}
	

	@Test // @Test makes the method testForNotNullUserService() to run as a JUnit test 
	public void testForNotNullUserService(){
		System.out.println("userService: "+ userService);
		assertNotNull(userService, "UserService is null, you must autowire it");
	}
	
	@Test
	public void testForEmptyString() {
		String str = "USA";
		assertTrue( str != null && str.length() != 0); // assertTrue: I expect result to be true, and the condition evaluates to true, test cases passes.
		assertFalse( str == null && str.length()==0); // assertFale: I expect false, and the test conditions evaluates to false, hence test cases passes.
		
		System.out.println("executed the method testForEmptyString()....");
	}
	
	@Test
	public void testForEquality() {
		Number number = Integer.valueOf(200);
		assertEquals(number, 200, "The number 200 equals 200");
		assertNotEquals(number, 250, "The number 200 is not equal to 250");
		
		// assertEquals(number, 100, "The number 200 is not equal to 100");   // does not pass
		// assertNotEquals(number, 200, "But the number 200 is equal to 200"); //does not pass
		System.out.println("executed the method testForEquality()....");
	}
	
	@Test
	public void testForObjectReferences() {
		String str1 = "Blaz";
		String str2 = str1;
		String str3 ="Menglee";
		
		assertSame(str1, str2); // str1 and str2 point to same string object in string pool.
		assertNotSame(str1, str3, "str1 and str3 are not same.");
		System.out.println("executed the method testForObjectReferences()....");
	}
	
	@BeforeEach   // Junit4 equivalent is @Before
	public void beforeTheExecutionOfEachTestCase() {
		System.out.println("### executes beforeTheExecutionOfEachTestCase() before each metod.");
	}
	
	@AfterEach   // Junit4 equivalent is @After
	public void afterTheExecutionOfEachTestCase() {
		System.out.println("*executes afterTheExecutionOfEachTestCase() after each metod. ###");
	}
	
	@BeforeAll  // JUnit4 equivalent is @BeforeClass
	public static void beforeAllMethods() {		
				System.out.println("[[ executes beforeAllMethods() ... before all methods. ");		
	}
	
	@AfterAll  // JUnit4 equivalent is @AfterClass
	public static void afterAllMethods() {		
				System.out.println(" executed afterAllMethods() ... after all methods have executed. ]]");		
	}
	
	
	@Test  // JUnit4 equivalent is: @Test(expected=NumberFormatException.class ), in Junit5 we have assertThrows(expectedType, executible code)
	public void testForExpectedException() {
		assertThrows(NumberFormatException.class, ()-> Integer.parseInt("hundred")); // test case passes for this
		// assertThrows(NumberFormatException.class, ()-> Integer.parseInt("100")); // test case fails here.
	}
	
	@Test
	public void testForExpectedException2() {
		assertThrows(IllegalArgumentException.class, ()->Integer.valueOf("One Hundred")); 
		/* IllegalArgumentException is expected, Integer.valueOf("One Hundred") throws same Exception. 
		Hence this test cases passes. */
		
		//assertThrows(IllegalArgumentException.class, ()->Integer.valueOf("25"));  
		// test case fails here as Integer.valueOf("25") does not throw IllegalArgumentException.
	}
	
	@Test  // JUnit4 equivalnent of timeout is @Test(timeout=60) // timeout in milliseconds
	public void timeoutDoesNotExceed() {
		assertTimeout(Duration.ofMinutes(1), () ->{System.out.println("###Executed with in 1 mintute"); });
	}
	
	@Disabled  // disabled test cases are not executed
	@Test
	public void timeoutExceeds() {  // test case fails as timeout is  10 milliseconds, but expected is 1 miiliseconds.
		assertTimeout(Duration.ofMillis(1), () ->{
			Thread.sleep(10);
			System.out.println("###Executed in more than 10 milli seconds");  
			
		});
	}
	
	@Test
	@Timeout(5)
	public void testForTimeoutUsingTimeoutAnnotation() throws InterruptedException { 
		// test case fails here as expected method execution time 5 milliseconds is less than 10 milliseconds
		TimeUnit.SECONDS.sleep(10);
	}

}
