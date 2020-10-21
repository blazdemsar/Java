package com.blazdemsar.integration;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.blazdemsar.integration.dto.Employee;

@Component
public class EmployeeRestClientImpl implements EmployeeRestClient {
	private static final String EMPLOYEE_URL = "http://localhost:8080/employee?id=";
	private static final String ALL_EMPLOYEES_URL = "http://localhost:8080/employees";
	private static String CREATE_EMPLOYEE_URL = "http://localhost:8080/createEmployee";
	private static String USER_NAME ="blaz";
	private static String PASSWORD ="blaz";
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public Employee findEmployeeById(int empId) {
		
		Employee employee = restTemplate.getForObject(EMPLOYEE_URL+empId, Employee.class);
		
		if(employee !=null) {
			
			return employee;
		} else {
			
			return null;
		}
	}
	
	@Override
	public ResponseEntity<Employee> findEmployeeById2(int empId) {
		/*
		HttpHeaders headers = new HttpHeaders();
		String auth = USER_NAME + ":"+PASSWORD;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF8")));
		String authHeader = "Basic "+ new String (encodedAuth);
		headers.set("Authorization", authHeader);
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		*/
		HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(getHttpHeaders());
		
		ResponseEntity<Employee> resEmployee = restTemplate.exchange(EMPLOYEE_URL+empId, HttpMethod.GET, requestEntity, Employee.class);
		//Employee employee = restTemplate.getForObject(EMPLOYEE_URL+empId, Employee.class);
		
			return resEmployee;
		
	}

	@Override
	public List<Employee> findAll() {
		Employee[] arrEmployee = restTemplate.getForObject(ALL_EMPLOYEES_URL, Employee[].class);
		List<Employee> employeeList = Arrays.asList(arrEmployee);
		return employeeList;
	}

	@Override
	public Employee save(Employee e) {
		
		//Create the messageConvertes for RestTemplate
		List<HttpMessageConverter<?>> messageConverters =new ArrayList<>();
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		
		//create Headers
		HttpHeaders headers= new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		// create request body
		HttpEntity<Employee> requestBody = new HttpEntity(e, headers);
		
		// send request using post
		Employee savedEmployee = restTemplate.postForObject(CREATE_EMPLOYEE_URL, requestBody, Employee.class);
		return savedEmployee;
	}

	@Override
	public ResponseEntity<String> deleteEmployeeById(int id) {
		
		String deleteUrl = "http://localhost:8080/deleteEmployee?id=" + id;
		
		restTemplate.delete(deleteUrl);
		
		return new ResponseEntity<String>("Employee with id=" +id +" has been deleted.", HttpStatus.GONE);
	}

	@Override
	public Employee updateEmployeeById(int id, String job, int salary) {
		
		Employee e = new Employee();
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("id", id);
		params.put("job", job);
		params.put("salary", salary);
		
		// http://localhost:8080/updateEmployee/1?salary=8000&designation=Sr. Programmer
		String updateUrl = "http://localhost:8080/updateEmployee/" + id + "?salary=" + salary + "&job=" + job;
		restTemplate.put(updateUrl, e, params);
		
		Employee employee = restTemplate.getForObject(EMPLOYEE_URL+id, Employee.class);
		return employee;
	}
	
	public static HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		String auth = USER_NAME + ":"+PASSWORD;
	//	byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF8")));
		String base64Credential =   Base64.encodeBase64String(auth.getBytes());
		String authHeader = "Basic "+ base64Credential;
		headers.set("Authorization", authHeader);
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
