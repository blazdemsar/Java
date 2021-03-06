package com.synergisticit.controller;

import org.aspectj.lang.annotation.DeclareError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.service.EmployeeService;
import com.synergisticit.validator.EmployeeValidator;

import java.util.List;

import javax.validation.Valid;

import com.synergisticit.domain.Employee;

@RestController
public class EmployeeRestController {
	// REST = REpresentational State Transfer
	/*
	REST is an architectural style or design method for restful web services. Web Services are exposed through only the HTTP methods.
	REST is a stateless architecture, no use of HttpSession.public EmployeeRestController() {
	HTTP Headers and Status codes are used to communicate with clients.
	Sun Micro Systems (Oracle) provides JAX-RS API for restful Web Services. Jersey is also in implementation. Spring Restful WebServices
	are provided by Spring framework that are based on Spring MVC framework and use controllers annotated with @RestController.
	*/
	
	/*@RestController is equivalent to @Controller +@ResponseBody. In rest controller all methods are annotaed with @ResponseBody by default.
	In Restful arhitecture anything on server is regarded as a "resource". Resources are uniformly accessible by 
	HTTP Methods(GET, PUT, POST, DELETE).
	
	Restful web services support multiple media types for communication. Spring Boot uses "json" by default. 
	We can use json, xml, text etc as media types.
	*/
	
	
	/* Idempotent Http Methods don't change the state of the resource on the server. GET method is Idempotent. 
	Delete and PUT are also Idempotent after the first request, becuase in first request there can be change in the state but
	in subsequent requests there is no change in state.
	POST method is not Idempotent.
	
	Usage:
	GET = select
	POST = create
	PUT = update
	DELETE = delete
	
	*/
	
	/*  
	Assignment:  Copy the existing restful webservice project to create a new project by changing the name of the project.
	The project name should reflect today's date and assignment. You should delete existing Controllers and create a new RestController 
	and implement the CRUD methods for Employee entity in this controller. You should test your methods using restful client PostMan.
	Ignore  typo if any and feel free to ask in case of any ambiguity.
	*/
	
	 @Autowired	 
	 @Qualifier(value="JpaRepoEmployeeServiceImpl")
	 EmployeeService employeeService;
	 
	 @Autowired
		EmployeeValidator employeeValidator;
		
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(employeeValidator);
	}
	 
	 @RequestMapping(value="employees", method=RequestMethod.GET)
	 public ResponseEntity<List<Employee>> getAllEmployees(){
		 List<Employee> listOfEmployees = employeeService.findAll();
		 if(listOfEmployees.isEmpty()) {
			 return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		 }else {
			 return new ResponseEntity<List<Employee>>(listOfEmployees, HttpStatus.ACCEPTED);
		 }
		 
	 }
	 
	// http://localhost:8080/employee/9
	// @RequestMapping(value="employee/{id}") // if Request Method is not mentioned, then by default it is Http GET
	 @RequestMapping(value="employee/{id}", method=RequestMethod.GET)
	 public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id){
		 Employee employee = employeeService.findById(id);
		 System.out.println("@EmployeeRestController...employee: "+employee);
		 if(employee == null) {
			// return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
			 return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		 }else {
			 return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
		 }
	 }
	 
	//http://localhost:8080/employee?id=9
		 @RequestMapping(value="employee", method=RequestMethod.GET, produces="application/json")
		 public ResponseEntity<Employee> getEmployeeByIdUsingRequestParam(@RequestParam("id") int id){
			 Employee employee = employeeService.findById(id);
			 if(employee == null) {
				// return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
				 return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
			 }else {
				 return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
			 }
		 }
		 
		 // localhost:8080/xml/employee?id=9
		 @RequestMapping(value="xml/employee", method=RequestMethod.GET, produces="application/xml")
		 public ResponseEntity<Employee> getEmployeeByIdUsingRequestParamXML(@RequestParam("id") int id){
			 Employee employee = employeeService.findById(id);
			 if(employee == null) {
				// return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
				 return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
			 }else {
				 return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
			 }
		 }		 
	 
		//http://localhost:8080/deleteEmployee?id=9
		// @RequestMapping(value="deleteEmployee") // when "method" attribute is not mentioned then Http GET is assumed.
		// @RequestMapping(value="deleteEmployee", method=RequestMethod.GET)  // as per design principal of restful webservices, http method GET should not be used to delete the resource.
		 @RequestMapping(value="deleteEmployee", method=RequestMethod.DELETE)
		 public ResponseEntity<?> deleteEmployeeById(@RequestParam("id") int id){
			
			 if(employeeService.existsById(id)) {
				 employeeService.deleteById(id);
				 return new ResponseEntity<String>("Employee record with id \""+id + "\" deleted", HttpStatus.ACCEPTED);
			 }else {
				 return new ResponseEntity<String>("No Employee with id \""+id + "\" exists.", HttpStatus.ACCEPTED);
			 }
			 
		 }
		 
		 @RequestMapping(value="createEmployee", method=RequestMethod.POST, consumes="application/xml", produces="application/xml")
		// @RequestMapping(value="createEmployee", method=RequestMethod.POST, consumes="application/xml", produces="application/json")
		// @RequestMapping(value="createEmployee", method=RequestMethod.POST, consumes="application/json", produces="application/xml")
		// @RequestMapping(value="createEmployee", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_XML_VALUE)
		// @RequestMapping(value="createEmployee", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
		 public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee, BindingResult br){
			 StringBuilder errorMessage = new StringBuilder("");
			 if(employeeService.existsById(employee.getEmpId()) || br.hasFieldErrors()) {
				 if(br.hasFieldErrors()) {
					 System.out.println("@EmployeeRestController hasFieldErrors....");
					 List<FieldError> errors = br.getFieldErrors();
					 for(FieldError fieldError: errors) {
						 errorMessage = errorMessage.append("\"" + fieldError.getField() +"\" " +fieldError.getDefaultMessage() +"\n");
						 System.out.println("errorMessage: "+errorMessage);
					 }
					 return new ResponseEntity<StringBuilder>(errorMessage, HttpStatus.FOUND);
				 }else {
				     return new ResponseEntity<String>("Employee with id \"" + employee.getEmpId() + "\" already exists.", HttpStatus.FOUND);
				 }
			 }else {
				 Employee e  = employeeService.save(employee);
				 return new ResponseEntity<Employee>(e, HttpStatus.CREATED);
			 }
		 }
		 
		 
		 // http://localhost:8080/updateEmployee/1?salary=8000&designation=Sr. Programmer
		 @RequestMapping(value="updateEmployee/{id}", method=RequestMethod.PUT)
		 public ResponseEntity<?>updateTheEmployee(@PathVariable int id, @RequestParam int salary, 
				 @RequestParam String designation){
			 Employee employeeToBeUpdated = employeeService.findById(id);
			 if(employeeToBeUpdated != null) {
				 employeeToBeUpdated.setSalary(salary);
				 employeeToBeUpdated.setDesignation(designation);
				 Employee e = employeeService.save(employeeToBeUpdated);
				 return new ResponseEntity<Employee>(e, HttpStatus.ACCEPTED);
			 }else {
				 return new ResponseEntity<String>("Employee with id "+ id + " does not exist.", HttpStatus.ACCEPTED);
			 }
			 
		 }
}

/*
<Employee>
<empId>9</empId>
<name>Rocky</name>
<designation>DBA MSQL</designation>
<salary>3500</salary>
<dob>807001200000</dob>
<gender>male</gender>
<spokenLanguages>
    <spokenLanguages>Spanish</spokenLanguages>
    <spokenLanguages>English</spokenLanguages>
    <spokenLanguages>Spanish</spokenLanguages>
    <spokenLanguages>English</spokenLanguages>
</spokenLanguages>
<skills>
    <skills>Java</skills>
    <skills>MySQL</skills>
</skills>
<hobbies>
    <hobbies>Cycling</hobbies>
</hobbies>
<employeeType>FULL_TIME</employeeType>
<insured>true</insured>
<email>ramesh@gmail.com</email>
<mobile>999976654343</mobile>
<nationality>India</nationality>
<address>
    <addressLine1>al1</addressLine1>
    <addressLine2>al2</addressLine2>
    <city>city1</city>
    <state>city2</state>
</address>
</Employee>
*/
