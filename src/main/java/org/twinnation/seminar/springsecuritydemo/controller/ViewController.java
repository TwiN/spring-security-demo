package org.twinnation.seminar.springsecuritydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
public class ViewController {
	
	@GetMapping("/")
	public ModelAndView index(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("user", principal);
		return modelAndView;
	}
	
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView admin(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("admin");
		modelAndView.addObject("user", principal);
		return modelAndView;
	}
	
}
