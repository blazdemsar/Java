package com.blazdemsar.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.blazdemsar.repository.UserRepository;

@Component  // must use @Component annotation here.
@Order(value=1)  // Lower the Order value, higher is the priority of execution.
public class XYZClass implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("=======================Output from run() method of XYZClass===============");
		userRepository.findAll().forEach((user) -> {
			System.out.println("xyzUser: "+ user.getName() + ", "+user.getEmail() + ", "+user.getRoles());
		}); 

	}

}
