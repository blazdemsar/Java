package com.blazdemsar.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.domain.Employee;
import com.blazdemsar.service.EmployeeService;
import com.blazdemsar.validator.EmployeeValidator;

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
		System.out.println("@EmployeeController.getEmployeeForm(...)........employee:"+employee);
		System.out.println("br.hasErrors(): "+br.hasErrors() );
		if(! br.hasErrors()) {
		employeeService.save(employee);
		}
		employee = new Employee();
		
		modelMap.addAttribute("spokenLanguages", spokenLanguages())
		        .addAttribute("hobbies", hobbies())
		        .addAttribute("employees", employeeService.findAll())
		        .addAttribute("employee", employee);
		        
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
