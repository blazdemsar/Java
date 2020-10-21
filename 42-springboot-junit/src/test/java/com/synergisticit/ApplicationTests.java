package com.synergisticit;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertNull;


import org.junit.jupiter.api.Test;
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
	

	@Test // @Test makes the method contextLoads() to run as a JUnit test 
	public void testForNotNullUserService(){
		System.out.println("userService: "+ userService);
		assertNotNull(userService, "UserService is null, you must autowire it");
	}

}
