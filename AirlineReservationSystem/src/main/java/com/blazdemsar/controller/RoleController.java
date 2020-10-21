package com.blazdemsar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blazdemsar.domain.Role;
import com.blazdemsar.domain.User;
import com.blazdemsar.service.RoleService;
import com.blazdemsar.service.UserService;

@Controller
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="role")
	public String getRoleForm(Role role, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("roles", roleService.findAll());
		
		return "role";
	}
	
	@RequestMapping(value="/saveRole")
	public String saveRole(@ModelAttribute Role role, BindingResult br, Model model) {
		
		System.out.println("@RoleController.saveRole(...)........ role: "+ role);
		
		if (!br.hasErrors()) {
			
			Role roleFromDb = roleService.save(role);
			
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		
		model.addAttribute("roles", roleService.findAll());
		
		return "role";
	}
}
