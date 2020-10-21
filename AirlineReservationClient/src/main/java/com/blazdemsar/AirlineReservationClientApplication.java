package com.blazdemsar;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SpringBootApplication
public class AirlineReservationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineReservationClientApplication.class, args);
	}
	
	/*
	 * @Bean public RestTemplate restTemplate() {
	 * 
	 * List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
	 * messageConverters.add(new StringHttpMessageConverter());
	 * messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
	 * messageConverters.add(new MappingJackson2HttpMessageConverter());
	 * 
	 * RestTemplate restTemplate = new RestTemplate();
	 * restTemplate.setMessageConverters(messageConverters);
	 * restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("blaz",
	 * "blaz", Charset.forName("UTF-8")));
	 * 
	 * return restTemplate; }
	 * 
	 * @Bean public ViewResolver viewResolver() { InternalResourceViewResolver
	 * viewResolver = new InternalResourceViewResolver();
	 * viewResolver.setPrefix("/WEB-INF/views/"); viewResolver.setSuffix(".jsp");
	 * viewResolver.setViewClass(JstlView.class);
	 * 
	 * return viewResolver; }
	 */

}
