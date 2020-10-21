package com.blazdemsar.controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.domain.Role;
import com.blazdemsar.domain.User;
import com.blazdemsar.service.RoleService;
import com.blazdemsar.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@RequestMapping(value="user")
	public String userForm(User user, Model model) {

		List<Role> roleList = roleService.findAll();
		Set<Role> roleSet = new LinkedHashSet<>(roleList);
		user.setRoles(roleSet);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currUser = userService.findByUsername(username);
		model.addAttribute("currentUser", currUser);

		model.addAttribute("roleSet", roleSet);
		model.addAttribute("users", userService.findAll());

		return "user";

	}

	@PostMapping("saveUser")
	public String saveUser(@Valid @ModelAttribute User user, BindingResult br, Model model) {

		System.out.println("@UserController.saveUser()....user: " + user);
		System.out.println("@UserController.saveUser()....roles: " + user.getRoles().toString());

		if (!br.hasErrors()) {

			if (user.getRoles().isEmpty()) {
				
				Role roleFromDb = roleService.findById((long)2);
				List<Role> roleList = new ArrayList<>();
				roleList.add(roleFromDb);
				Set<Role> roleSet = new LinkedHashSet<>(roleList);
				user.setRoles(roleSet);

				userService.save(user);
				
			} else {
				
				userService.save(user);
				
			}
			//SimpleMailMessage message = mailService.sendEmail(user.getName(), user.getEmail());
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currUser = userService.findByUsername(username);
		model.addAttribute("currentUser", currUser);

		List<Role> roleList = roleService.findAll();
		Set<Role> roleSet = new LinkedHashSet<>(roleList);
		user.setRoles(roleSet);
		model.addAttribute("roleSet", roleSet);
		model.addAttribute("users", userService.findAll());

		return "home";
	}

	@GetMapping(value = "updateUser")
	public String updateUser(User user, @RequestParam Long userId, Model model) {
		List<Role> roleList = roleService.findAll();
		Set<Role> roleSet = new LinkedHashSet<>(roleList);
		model.addAttribute("roleSet", roleSet);

		if (userService.existsById(userId)) {
			user = userService.findById(userId);
			model.addAttribute("user", user);
			Set<Role> selectedRoles = user.getRoles();
			model.addAttribute("selectedRoles", selectedRoles);
		} else {
			model.addAttribute("user", new User());
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currUser = userService.findByUsername(username);
		model.addAttribute("currentUser", currUser);

		model.addAttribute("users", userService.findAll());

		return "user";
	}

	@RequestMapping(value="deleteUser")
	public String deleteUser(User user, @RequestParam Long id, Model model) {
		System.out.println("@UserController.deleteUser(...)........ user: "+ user);

		userService.deleteById(id);
		model.addAttribute("users", userService.findAll());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User currUser = userService.findByUsername(username);
		model.addAttribute("currentUser", currUser);

		return "user";
	}
}
