package com.synergisticit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.Employee;
import com.synergisticit.service.EmployeeService;
import com.synergisticit.validator.EmployeeValidator;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeValidator employeeValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(employeeValidator);
	}
	
	@RequestMapping(value="employeeForm")
	public String getEmployeeForm(Employee employee, Model model) {
		System.out.println("@EmployeeController.getEmployeeForm(...)........");
		model.addAttribute("spokenLanguages", spokenLanguages());
		model.addAttribute("hobbies", hobbies());
		model.addAttribute("employees", employeeService.findAll());
		
		return "employeeForm";
	}
	
	@RequestMapping(value="saveEmployee")
	public String saveEmployeeForm(@Valid @ModelAttribute Employee employee, BindingResult br,  Model model) {
		System.out.println("@EmployeeController.getEmployeeForm(...)........employee:"+employee);
		System.out.println("br.hasErrors(): "+br.hasErrors() );
		if(! br.hasErrors()) {
		employeeService.save(employee);
		}
		
		model.addAttribute("spokenLanguages", spokenLanguages());
		model.addAttribute("hobbies", hobbies());
		model.addAttribute("employees", employeeService.findAll());
		
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

}
