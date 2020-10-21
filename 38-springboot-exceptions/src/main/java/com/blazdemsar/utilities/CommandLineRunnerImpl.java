package com.blazdemsar.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.blazdemsar.repository.UserRepository;

@Component
@Order(value=1)
public class CommandLineRunnerImpl implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("=============Output from run() method of CommandLineRunnerImpl1=============");
		
		userRepository.findAll().forEach(user -> {
			System.out.println("User: " + user.getName() + ", " + user.getEmail() + ", " + user.getRoles());
		});

	}

}
