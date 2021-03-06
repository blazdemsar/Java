package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.User;
import com.synergisticit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
   
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	
	@Override
	public User createUser(User user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public User findById(Long id) {
		Optional<User> optUser = userRepository.findById(id);
		if(optUser.isPresent()) {
			return optUser.get();
		}
		return null;
	}

}
