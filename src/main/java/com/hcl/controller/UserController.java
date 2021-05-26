package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import com.hcl.model.Authority;
import com.hcl.auth.GetAuthImpl;
import com.hcl.model.User;
import com.hcl.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private GetAuthImpl authState;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/")
	public ModelAndView landingPage() {
		return new ModelAndView("create","user", new User());
	}
	
	@GetMapping(value="/create")
	public ModelAndView createPage() {
		return new ModelAndView("create","user", new User());
	}
	
	@PostMapping(value="/create")
	public ModelAndView createUser(@ModelAttribute User user) {
//		System.out.println(user.getUsername());
		int createUser = userService.createUser(user);
		if(createUser == 0) {
			System.out.println(authState.getAuth().getAuthorities());
			return new ModelAndView("redirect:/login");
		}else if(createUser == 1) {
			return new ModelAndView("create", "errorMsg", "Username is in use already!");
		}
		return new ModelAndView("create", "errorMsg", "Your username or password were blank");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model, String error, String logout) {
		if(authState.getAuth().getAuthorities().toString().equals("[ROLE_USER]")) {
			return new ModelAndView("redirect:/user");
		}
		if (error != null) {
			model.addAttribute("errorMsg", "Your username and password are invalid.");
			return new ModelAndView("login");
		}

		if (logout != null) {
			model.addAttribute("msg", "You have been logged out successfully.");
			return new ModelAndView("login");
		}
		return new ModelAndView("login");
	}
	
	@GetMapping(value="/user")
	public ModelAndView successfulLogin() {
		return new ModelAndView("user", "username", authState.getAuth().getName());
	}
}
