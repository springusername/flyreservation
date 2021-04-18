package com.sine.student.flghtreservation.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sine.student.flghtreservation.entities.User;
import com.sine.student.flghtreservation.repos.UserRepository;
import com.sine.student.flghtreservation.services.SecurityService;

import ch.qos.logback.classic.Logger;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private SecurityService securityService;
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage()");
		
		return "login/registerUser";
	}
	
	@RequestMapping("/showLogin")
	public String showLoginnPage() {
		LOGGER.info("Inside showLoginnPage()");

		return "login/login";
	}
	
	@RequestMapping(value="registerUser", method=RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register()"+user);
		
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("email") String email,@RequestParam("password")
	String password,ModelMap modelMap) {
		
		LOGGER.info("Inside login");
		boolean loginResponse = securityService.login(email, password);
		//User user = userRepository.findByEmail(email);
		if (loginResponse) {
			return "findFlights";
		}
		else {
			modelMap.addAttribute("msg","Invalid user name or password. Please try again.");
		}
		
		return "login/login";
	}
}
