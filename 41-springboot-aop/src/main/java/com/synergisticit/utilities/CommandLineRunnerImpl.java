package com.synergisticit.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.synergisticit.repository.UserRepository;

@Component  // @Component annotation is must. We should not use @Service, @Repository etc here as per convention and intention of use.
@Order(value=3) // Higher the order value lower is the priority.
public class CommandLineRunnerImpl implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("=======================Output from run() method of CommandLineRunner===============");
		userRepository.findAll().forEach((user) -> {
			System.out.println("*User: "+ user.getName() + ", "+user.getEmail() + ", "+user.getRoles());
		}); 

	}

}
