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
	// Used to check auth status of current user
	private GetAuthImpl authState;
	
	@Autowired
	// User service for creating user
	UserService userService;
	
	@GetMapping(value="/")
	public ModelAndView landingPage() {
		// Create user page
		return new ModelAndView("create","user", new User());
	}
	
	@GetMapping(value="/create")
	public ModelAndView createPage() {
		// Create user page
		return new ModelAndView("create","user", new User());
	}
	
	@PostMapping(value="/create")
	public ModelAndView createUser(@ModelAttribute User user) {
		// Calls the create user and gets return code
		int createUser = userService.createUser(user);
		// Depending on return code return correct page and possible error msg
		if(createUser == 0) {
			System.out.println(authState.getAuth().getAuthorities());
			// Redirects to login if create user is successful
			return new ModelAndView("redirect:/login");
		}else if(createUser == 1) {
			return new ModelAndView("create", "errorMsg", "Username is in use already!");
		}
		return new ModelAndView("create", "errorMsg", "Your username or password were blank or too long");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model, String error, String logout) {
		// Checks if the user is already logged in before allowing them to view the login screen
		if(authState.getAuth().getAuthorities().toString().equals("[ROLE_USER]")) {
			return new ModelAndView("redirect:/user");
		}
		// Returns login page with error msg if incorrect, if correct the Spring Security
		// Will reroute to user page
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
		// Displays the user page and passes the username to display
		return new ModelAndView("user", "username", authState.getAuth().getName());
	}
}
