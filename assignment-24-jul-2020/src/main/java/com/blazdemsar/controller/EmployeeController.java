package com.blazdemsar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.domain.Employee;
import com.blazdemsar.service.EmployeeService;

@Controller
public class EmployeeController {
	
	EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@RequestMapping("/employeeForm")
	public String employeeForm(Employee employee, Model model) {
		
		System.out.println("Inside of EmployeeController.employeeForm().....");
		model.addAttribute("employees", employeeService.findAll());
		return "employeeForm";
	}
	
	@RequestMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee, Model model) {
		
		System.out.println("Inside of EmployeeController.saveEmployee.....");
		model.addAttribute("employee", employee);
		Employee e = employeeService.findById(employee.getEmpId());
		if (e == null) {
			employeeService.save(e);
		} else {
			employeeService.updateById(e.getEmpId());
		}
		model.addAttribute("employees", employeeService.findAll());
		return "employeeForm";
	}
	
	@RequestMapping("/findEmployee")
	public String findEmployee(@RequestParam int empId, Model model) {
		
		System.out.println("Inside of EmployeeController.findEmployee.....");
		model.addAttribute("employee", employeeService.findById(empId));
		return "employeeForm";
	}
	
	@RequestMapping("/updateEmp")
	public String updateEmployee(@ModelAttribute Employee employee, @RequestParam int empId, Model model) {
		
		System.out.println("Inside of EmployeeController.updateEmployee.....");
		model.addAttribute("employee", employeeService.findById(empId));
		model.addAttribute("employees", employeeService.findAll());
		return "employeeForm";
	}
	
	@RequestMapping("/deleteEmployee")
	public String deleteEmployee(Employee employee, @RequestParam int empId, Model model) {
		
		System.out.println("Inside of EmployeeController.deleteEmployee.....");
		employeeService.deleteById(empId);
		model.addAttribute("employees", employeeService.findAll());
		return "employeeForm";
	}
}
