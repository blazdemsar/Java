package com.synergisticit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Employee;
import com.synergisticit.domain.Passport;
import com.synergisticit.domain.Project;
import com.synergisticit.repository.EmployeeRepository;
import com.synergisticit.repository.PassportRepository;
import com.synergisticit.repository.ProjectRepository;
import com.synergisticit.service.EmployeeService;
import com.synergisticit.utilities.EmployeeNotFoundException;
import com.synergisticit.validator.EmployeeValidator;

@Controller
public class EmployeeController {
	
	@Autowired
	//@Qualifier(value="hibernateEmployeeService")
	@Qualifier(value="JpaRepoEmployeeServiceImpl")
	EmployeeService employeeService;
	
	
	  @Autowired EmployeeValidator employeeValidator;
	 
	
	@Autowired
	EmployeeRepository employeeRepository ;
	
	@Autowired
	PassportRepository passportRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	
	
	  @InitBinder 
	  public void initBinder(WebDataBinder binder) {
		  binder.addValidators(employeeValidator); 
	  }
	 
	
//	@PreAuthorize(value = "hasAuthority('DBA')")
	@RequestMapping(value="employeeForm")
	public String getEmployeeForm(Employee employee, Model model) {
		System.out.println("@EmployeeController.getEmployeeForm(...)........employee:"+employee);
		model.addAttribute("spokenLanguages", spokenLanguages());
		model.addAttribute("hobbies", hobbies());
		model.addAttribute("employees", employeeService.findAll());
		
		return "employeeForm";
	}
	
	/*
	@RequestMapping(value="saveEmployee")
	public String saveEmployeeForm(@Valid @ModelAttribute Employee employee, BindingResult br,  Model model) {
		System.out.println("@EmployeeController.getEmployeeForm(...)........employee:"+employee);
		System.out.println("br.hasErrors(): "+br.hasErrors() );
		if(! br.hasErrors()) {
		employeeService.save(employee);
		}
		employee = new Employee();
		model.addAttribute("spokenLanguages", spokenLanguages());
		model.addAttribute("hobbies", hobbies());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("employee", employee);
		return "employeeForm";
	}
	*/
	
	// Use of ModelAndView
/*	
	@RequestMapping(value="saveEmployee")
	public ModelAndView saveEmployeeForm(@Valid @ModelAttribute Employee employee, BindingResult br) {
		ModelAndView mv = new ModelAndView("employeeForm");
		System.out.println("@EmployeeController.getEmployeeForm(...)........employee:"+employee);
		System.out.println("br.hasErrors(): "+br.hasErrors() );
		if(! br.hasErrors()) {
		employeeService.save(employee);
		}
		employee = new Employee();
		mv.addObject("spokenLanguages", spokenLanguages());
		mv.addObject("hobbies", hobbies());
		mv.addObject("employees", employeeService.findAll());
		
		mv.addObject("employee", employee);
		return mv ;
	}
	*/
	
	// Use of ModelMap that provides chained methods to add the attributes
	@RequestMapping(value="saveEmployee")
	public String saveEmployeeForm(@Valid @ModelAttribute Employee employee, BindingResult br,  ModelMap modelMap) {
		//employeeValidator.validate(employee, br);
		System.out.println("@EmployeeController.getEmployeeForm(...)........employee:"+employee);
		System.out.println("br.hasErrors(): "+br.hasErrors() );
		
      
		modelMap.addAttribute("spokenLanguages", spokenLanguages())
        .addAttribute("hobbies", hobbies());
		
		if(br.hasErrors()) {
			System.out.println("@EmployeeController.getEmployeeForm(...)....employee: "+ employee);
		  List<FieldError> errors = br.getFieldErrors();
		  for(FieldError error: errors) {
			  System.out.println("***"+ error.getField() + ": "+ error.getDefaultMessage());
		  }
		}else {
			System.out.println("@EmployeeController.getEmployeeForm(...)....before a call to employeeService.save(employee):"+employee);
			Passport passport = new Passport(employee.getPassport().getPassportNumber());
			passportRepository.save(passport);
			employee.setPassport(passport);
			
			Project project = new Project(employee.getProject().getName());
			projectRepository.save(project);
			employee.setProject(project);
			
			employeeService.save(employee);
			modelMap.addAttribute("employee", new Employee());
			
		}
		
		modelMap.addAttribute("employees", employeeService.findAll());
       	
		        
		return "employeeForm";
	}
	
	
	
	@RequestMapping(value="delete")
	public String delete(Employee employee, @RequestParam int empId, Model model) {
		employeeService.deleteById(empId);
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("spokenLanguages", spokenLanguages());
		model.addAttribute("hobbies", hobbies());
		return "employeeForm";
	}
	
	@RequestMapping(value="update")
	public String update(Employee employee, @RequestParam int empId, Model model) {
		System.out.println("@EmployeeController.update(...)....empId: "+empId);
		Employee e = employeeService.findById(empId);
		model.addAttribute("employee", e);
		model.addAttribute("spokenLanguages", spokenLanguages());
		model.addAttribute("hobbies", hobbies());
		model.addAttribute("employees", employeeService.findAll());
		return "employeeForm";
	}
	
	public List<String>  spokenLanguages(){
		List<String>  spokenLanguages = new ArrayList<>();
		spokenLanguages.add("English");
		spokenLanguages.add("Spanish");
		spokenLanguages.add("French");
		spokenLanguages.add("German");
		spokenLanguages.add("Hindi");
		spokenLanguages.add("Arabic");
		spokenLanguages.add("Chinese");
		spokenLanguages.add("Others");
		return spokenLanguages;
	}
	
	public String[] hobbies() {
		String[] hobbies = {"Cricket", "FootBall", "Badminton", "Cycling", "Swimming", "Morning Walk"};
		return hobbies;
	}
	
	@RequestMapping(value="findAllEmployeesSorted")
	public ResponseEntity<List<Employee>> findAllEmployeesSortedBy(@RequestParam(required=false) String str1,
			@RequestParam(required=false) String str2){
		
		if((str1 != null) && (str2 != null) ) {
		return new ResponseEntity<List<Employee>>(employeeRepository.findAll(Sort.by(Sort.Direction.DESC, str1, str2)), HttpStatus.ACCEPTED);
		}
		
		if((str1 == null) && (str2 != null) ) {
			return new ResponseEntity<List<Employee>>(employeeRepository.findAll(Sort.by(str2)), HttpStatus.ACCEPTED);
		}
		
		if((str1 != null) && (str2 == null) ) {
			return new ResponseEntity<List<Employee>>(employeeRepository.findAll(Sort.by(Sort.Direction.DESC, str1)), HttpStatus.ACCEPTED);
		}

		
		return new ResponseEntity<List<Employee>>(employeeRepository.findAll(), HttpStatus.ACCEPTED);
		
	}

	//@RequestMapping(value="employee/insert")
	//@RequestMapping(value="employee/insert", method=RequestMethod.GET)
	@GetMapping(value="employee/insert") 
	// we can use @GetMapping, @PostMapping, @PutMapping, @DeleteMapping from spring 4.x, there is no need to mention the http method
	public ResponseEntity<String> insertUsingQueryAnnotation(@RequestParam int empId, @RequestParam String name,
			@RequestParam String designation, @RequestParam int salary, @RequestParam boolean insured ){
		
		employeeService.insertUsingQueryAnnotation(empId, name, designation, salary, insured);
		return new ResponseEntity<String>("Employee data saved.", HttpStatus.CREATED);
		
	}
	
	
	//@GetMapping(value="employee/update") // supported by both the browser and Rest Clinet (Postman)
	@PutMapping(value="employee/update")  // Browser does not support Http POST, so @PutMapping does not work with browser, but it works fine with Rest client Postman.
	// we can use @GetMapping, @PostMapping, @PutMapping, @DeleteMapping from spring 4.x, there is no need to mention the http method
	public ResponseEntity<String> updateSalaryByEmpIdUsingQueryAnnotation(@RequestParam int empId,  @RequestParam int salary){
		
		employeeService.updateSalaryByEmpIdUsingQueryAnnotation(empId, salary);
		return new ResponseEntity<String>("Employee data updated.", HttpStatus.ACCEPTED);
		
	}
	
	    @GetMapping(value="employee/delete") // supported by both the browser and Rest Clinet (Postman)
		//@DeleteMapping(value="employee/delete")  // Browser does not support Http POST, so @PutMapping does not work with browser, but it works fine with Rest client Postman.
		// we can use @GetMapping, @PostMapping, @PutMapping, @DeleteMapping from spring 4.x, there is no need to mention the http method
		public ResponseEntity<String> deleteByIdUsingQueryAnnotation(@RequestParam int empId){
			
			employeeService.deleteByIdUsingQueryAnnotation(empId);
			return new ResponseEntity<String>("Employee data was deleted.", HttpStatus.GONE);
			
		}
	    
	    
	    @GetMapping(value="employee/get") // supported by both the browser and Rest Clinet (Postman)
		public ResponseEntity<String> getByIdUsingQueryAnnotation(@RequestParam int empId){
			
			Employee e = employeeService.getById(empId);
			return new ResponseEntity<String>("Employee deatails are as below:</br>"+e, HttpStatus.GONE);
			
		}
	    
	   // 18-Aug-2020
	    @RequestMapping(value="findById")
	    public ResponseEntity<?> findById(@RequestParam int empId){
	    	Employee e = employeeService.getById(empId);
	    	
	    	if(e == null) throw new EmployeeNotFoundException(empId);
	    	return new ResponseEntity<Employee>(e, HttpStatus.FOUND);
	    	
	    	/*
	    	if(e == null) {
	    		return new ResponseEntity<String>("There is no employee whose id is "+empId+".", HttpStatus.NOT_FOUND);
	    	}else {
	    		return new ResponseEntity<Employee>(e, HttpStatus.FOUND);
	    	}
	    	*/
	    }
	    
	    //http://localhost:8085/findByName?name=dinesh	 
	    @RequestMapping(value="findByName")
	    public ResponseEntity<?> findByName(@RequestParam String name){
	    	
	    	// int x = 100;
	    	// int y = 100/0;
	    	List<Employee> employees = employeeService.findByName(name);
	    	
	    	/*
	    	// Following code snippet  shows how we can handle the Exception using @ControllerAdvice
	    	
	    	if(employees.isEmpty()) throw new EmployeeNotFoundException("There is no employee whose name is \"" +name +"\"");
	    	return new ResponseEntity<List<Employee>>(employees, HttpStatus.FOUND);
	    	
	    	
	    	// Following code snippet  shows how we can handle the situation without any exception being thrown.
	    	
	    	if(employees.isEmpty()) {
	    		return new ResponseEntity<String>("There is no employee whose name is "+name+".", HttpStatus.NOT_FOUND);
	    	}else {
	    		return new ResponseEntity<List<Employee>>(employees, HttpStatus.FOUND);
	    	}
	    	*/
	    	return new ResponseEntity<List<Employee>>(employees, HttpStatus.FOUND);
	    }
}
