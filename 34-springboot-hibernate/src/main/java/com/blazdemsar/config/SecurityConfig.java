package com.blazdemsar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration

//method level security. "securedEnabled" corresponds to @Secured annotation on methods and @PreAuthorzed and @PostAuthorized methods.
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true) 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AccessDeniedHandler accessDeniedHandler;

	@Override
	protected void 	configure(AuthenticationManagerBuilder auth) throws Exception{
		/*
		auth.inMemoryAuthentication().withUser("blaz").password("{noop}blaz").roles("user", "programmer");
		auth.inMemoryAuthentication().withUser("menglee").password("{noop}menglee").roles("user", "programmer");
		auth.inMemoryAuthentication().withUser("dinesh").password("{noop}dinesh").roles("user", "programmer");
		*/
		
	//	auth.inMemoryAuthentication().withUser("blaz").password(encoder.encode("blaz")).roles("user", "programmer");
	//	auth.inMemoryAuthentication().withUser("menglee").password(encoder.encode("menglee")).roles("user", "programmer");
		
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String blazPassword = "blaz";
		String mengleePassword = "menglee";
		String blazEncodedPassword = bCryptPasswordEncoder.encode(blazPassword);
		String mengleeEncodedPassword = bCryptPasswordEncoder.encode(mengleePassword);
		System.out.println("blaz encoded password: "+blazEncodedPassword + ", size:"+blazEncodedPassword.length());
		System.out.println("menglee encoded password: " + mengleeEncodedPassword + ", size:"+mengleeEncodedPassword.length());
		return bCryptPasswordEncoder;
	}
	
	@Override
	protected void 	configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/getUserForm","/saveUser").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		//.exceptionHandling().accessDeniedPage("/accessDeniedPage") // to handle the resource that are denied for a particular role
		.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
		.and()
		.logout().permitAll()
		.and()
		.httpBasic()
		.and()
		.csrf().disable();  // cross-site-resource-forgery should be disabled, because server and client are on the same machine.
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/img/*", "/css/*");
	}
	
}




