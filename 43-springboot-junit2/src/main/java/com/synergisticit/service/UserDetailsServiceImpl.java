package com.synergisticit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.Role;

import java.util.Set;
import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username: "+username);
		
		com.blazdemsar.domain.User userEntity = userService.findByName(username);
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		for( Role role : userEntity.getRoles() ) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			System.out.println("role: "+role);
		}
		
		return new org.springframework.security.core.userdetails.User(userEntity.getName(), userEntity.getPassword(), authorities);
		//return new User(userEntity.getName(), userEntity.getPassword(), authorities);
	}

}
