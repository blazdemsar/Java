package com.blazdemsar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.service.EmployeeService;

import java.util.List;
import com.blazdemsar.domain.Employee;

@RestController
public class EmployeeRestController {

	// REST = REpresentational State Transfer
	/*
	 * REST is an architectural style or design method for restful web services. Web
	 * Services are exposed through only the HTTP methods. REST is a stateless
	 * architecture, no use of HttpSession.public EmployeeRestController() { HTTP
	 * Headers and Status codes are used to communicate with clients. Sun Micro
	 * Systems (Oracle) provides JAX-RS API for restful Web Services. Jersey is also
	 * in implementation. Spring Restful WebServices are provided by Spring
	 * framework that are based on Spring MVC framework and use controllers
	 * annotated with @RestController.
	 */

	/*
	 * @RestController is equivalent to @Controller +@ResponseBody. In rest
	 * controller all methods are annotaed with @ResponseBody by default. In Restful
	 * arhitecture anything on server is regarded as a "resource". Resources are
	 * uniformly accessible by HTTP Methods(GET, PUT, POST, DELETE).
	 * 
	 * Restful web services support multiple media types for communication. Spring
	 * Boot uses "json" by default. We can use json, xml, text etc as media types.
	 */

	/*
	 * Idempotent Http Methods don't change the state of the resource on the server.
	 * GET method is Idempotent. Delete and PUT are also Idempotent after the first
	 * request, becuase in first request there can be change in the state but in
	 * subsequent requests there is no change in state. POST method is not
	 * Idempotent.
	 * 
	 * Usage: GET = select POST = create PUT = update DELETE = delete
	 * 
	 */

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "employees", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> listOfEmployees = employeeService.findAll();
		if (listOfEmployees.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Employee>>(listOfEmployees, HttpStatus.ACCEPTED);
		}

	}

	// http://localhost:8080/employee/9
	// @RequestMapping(value="employee/{id}") // if Request Method is not mentioned,
	// then by default it is Http GET
	@RequestMapping(value = "employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			// return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
		}
	}

	// http://localhost:8080/employee?id=9
	@RequestMapping(value = "employee", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeByIdUsingRequestParam(@RequestParam("id") int id) {
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			// return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
		}
	}

	// http://localhost:8080/employee?id=9
	@RequestMapping(value = "xml/employee", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<Employee> getEmployeeByIdUsingRequestParamXML(@RequestParam("id") int id) {
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			// return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
		}
	}

	// http://localhost:8080/deleteEmployee?id=9
	// @RequestMapping(value="deleteEmployee") // when "method" attribute is not
	// mentioned then Http GET is assumed.
	// @RequestMapping(value="deleteEmployee", method=RequestMethod.GET) // as per
	// design principal of restful webservices, http method GET should not be used
	// to delete the resource.
	@RequestMapping(value = "deleteEmployee", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployeeById(@RequestParam("id") int id) {

		if (employeeService.existsById(id)) {
			employeeService.deleteById(id);
			return new ResponseEntity<String>("Employee record with id \"" + id + "\" deleted", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("No Employee with id \"" + id + "\" exists.", HttpStatus.ACCEPTED);
		}

	}

	//@RequestMapping(value = "createEmployee", method = RequestMethod.POST, consumes = "application/xml", produces = "application/xml")
	//@RequestMapping(value = "createEmployee", method = RequestMethod.POST, consumes = "application/xml", produces = "application/json")
	//@RequestMapping(value = "createEmployee", method = RequestMethod.POST, consumes = "application/json", produces = "application/xml")
	@RequestMapping(value = "createEmployee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		if (employeeService.existsById(employee.getEmpId())) {
			return new ResponseEntity<String>("Employee with id \"" + employee.getEmpId() + "\" already exists.",
					HttpStatus.FOUND);
		} else {
			Employee e = employeeService.save(employee);
			return new ResponseEntity<Employee>(e, HttpStatus.CREATED);
		}
	}

	// http://localhost:8080/updateEmployee/1?salary=8000&job=Sr. Programmer
	@RequestMapping(value = "updateEmployee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTheEmployee(@PathVariable int id, @RequestParam int salary,
			@RequestParam String job) {
		Employee employeeToBeUpdated = employeeService.findById(id);
		if (employeeToBeUpdated != null) {
			employeeToBeUpdated.setSalary(salary);
			employeeToBeUpdated.setJob(job);
			Employee e = employeeService.save(employeeToBeUpdated);
			return new ResponseEntity<Employee>(e, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Employee with id " + id + " does not exist.", HttpStatus.ACCEPTED);
		}
	}
}
