package com.blazdemsar.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.blazdemsar.repository.UserRepository;

@Component  //@Component annotation is must
@Order(value=2) // Lower the Order value, higher is the priority of execution.
public class CommandLineRunnerImpl2 implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("=======================Output from run() method of CommandLineRunnerImpl2===============");
		userRepository.findAll().forEach((user) -> {
			System.out.println("**User: "+ user.getName() + ", "+user.getEmail() + ", "+user.getRoles());
		}); 

	}

}
