package com.blazdemsar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // @Controller marks this class as a bean that can handle http request, based on 
			// request mapping specified on class or methods in the controllers
public class HelloController {
	
	@RequestMapping("/hello")
	public String hello(Model model) {
		
		System.out.println("Inside of HelloController.hello!");
		model.addAttribute("helloBlaz", "Hello Blaz!");
		model.addAttribute("helloDomen", "Hello Domen!");
		
		return "helloString";
		
		  /* here "helloString" is the name of the view. DispatcherController
		     takes the help of viewResolver and InternalResourceViewResolver
		     to get the location of the view name.
		     The InternalResourceViewResolver prefixes "/WEB-INF/views/" to
		     the view name i.e. "helloString" and suffixes ".jsp" to "helloString"
		  */
	}
	
	public String sayHello(Model model) {
		
		System.out.println("Inside of HelloController.sayHello!");
		model.addAttribute("helloBlaz", "Hello Blaz!");
		model.addAttribute("helloDomen", "Hello Domen!");
		
		return "helloString";
	}
}
