package com.blazdemsar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.blazdemsar.domain.Employee;
import com.blazdemsar.repository.EmployeeRepository;

import java.util.List;

@Controller
public class PagedEmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository ;
	
	@RequestMapping(value="pagedEmployee")
	public String findThePagedEmployee(@RequestParam int pageNo, 
			@RequestParam int pageSize,
			@RequestParam(required=false) String sortedBy,
			Model model
			
			) {
		
		Pageable pageable;
		
		System.out.println("pageNo: "+ pageNo + ", pageSize: "+ pageSize + ", sortedBy: "+sortedBy);
		if(sortedBy != null) {
			pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy).descending());
		}else {
			pageable = PageRequest.of(pageNo, pageSize);
		}
		
		Page<Employee> pagedEmployees = employeeRepository.findAll(pageable);
		List<Employee> employeesOnPage  =  pagedEmployees.getContent();
		model.addAttribute("employeesOnPage", employeesOnPage);
		model.addAttribute("totalPages", pagedEmployees.getTotalPages());
		
		return "employeeList";
	}

}
