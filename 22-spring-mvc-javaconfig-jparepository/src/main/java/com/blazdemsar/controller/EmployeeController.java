package com.blazdemsar.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blazdemsar.domain.Employee;
import com.blazdemsar.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value="employeeFForm")
	public String getEmployeeForm(Employee employee, Model model) {
		System.out.println("@EmployeeController.getEmployeeForm....");
		model.addAttribute("spokenLanguages", spokenLanguages());
		model.addAttribute("hobbies", hobbies());
		model.addAttribute("employees", employeeService.findAll());
		return "employeeForm";
	}
	
	@RequestMapping(value="saveEmployee")
	public String saveEmployeeForm(@ModelAttribute Employee employee, Model model) {
		System.out.println("@EmployeeController.saveEmployeeForm.... employee : " + employee);
		return "employeeForm";
	}
	
	public List<String> spokenLanguages() {
		List<String> spokenLanguages = new ArrayList<>();
		spokenLanguages.add("English");
		spokenLanguages.add("Slovene");
		spokenLanguages.add("German");
		spokenLanguages.add("Serbian");
		spokenLanguages.add("Spanish");
		
		return spokenLanguages;
	}
	
	public String[] hobbies() {
		String[] hobbies = {"Swimming", "Football", "Basketball", "GYMSHARK"};
		
		return hobbies;
	}
}
