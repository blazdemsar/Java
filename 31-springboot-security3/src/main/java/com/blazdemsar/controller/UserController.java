package com.blazdemsar.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	
	@RequestMapping(value="createUser", method=RequestMethod.GET)
	public ResponseEntity<User> createUser(@RequestParam String name, @RequestParam String password, 
			@RequestParam String email){
		
		User user = new User(name, password, email);
		List<Role> rolesFromDb = roleService.findAll();
		
		Set<Role> roles = new HashSet<>();
		/*
		roles.add(rolesFromDb.get(0));
		roles.add(rolesFromDb.get(1));
		*/
		roles.add(rolesFromDb.get(2));
		roles.add(rolesFromDb.get(3));
		user.setRoles(roles);
		
		User userFromDb = userService.createUser(user);
		
		return new ResponseEntity<User>(userFromDb, HttpStatus.CREATED);
	}
	

   @GetMapping (value= {"getUserForm", "/" })	
   public String userForm(User user, Model model) {
	   List<Role> roleList = roleService.findAll();
	   Set<Role> roleSet = new HashSet<>(roleList);
	   user.setRoles(roleSet);
	   
	   model.addAttribute("roleSet", roleSet);
	   model.addAttribute("users", userService.findAll());
	   return "user";
   }
   
   @PostMapping("saveUser")
   public String saveUser(@Valid @ModelAttribute User user, BindingResult br, Model model ) {
	   
	   if(! br.hasErrors()) {
		   userService.createUser(user);
		   
		   List<Role> roleList = roleService.findAll();
		   Set<Role> roleSet = new HashSet<>(roleList);
		   user.setRoles(roleSet);
		   model.addAttribute("roleSet", roleSet);
		   model.addAttribute("users", userService.findAll());
	   }
	   
	   return "user";
   }
   
   @GetMapping("login")
   public String login(@RequestParam(value="logout", required=false) String logout,
		   @RequestParam(value="error", required=false) String error,
		   HttpServletRequest request, HttpServletResponse response, Model model
		   
		   ) {
	   String message = null;
	   System.out.println("@UserController.login() ....");
	   
	   if(logout != null) {
		   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   if(authentication !=null ) {
			   new SecurityContextLogoutHandler().logout(request, response, authentication);
		   }
		   message = "You have been logged out successfully.";
		   return "login";
	   }
	   
	   if(error != null) {
		   message = "Either username or password is wrong.";
	   }
	   
	   model.addAttribute("message", message);
	   return "login";
	   
   }
   
   @GetMapping(value="accessDeniedPage")
   public String accessDenied() {	   
	   return "accessDenied";
   }

}
