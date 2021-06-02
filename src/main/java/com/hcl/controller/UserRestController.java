package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.auth.GetAuthImpl;
import com.hcl.model.User;
import com.hcl.service.UserService;

@RestController(value = "/rest")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private GetAuthImpl authState;

	@PostMapping(produces = "application/json")
	public ResponseEntity<Void> createAcc(@RequestBody User user) {
		if (userService.createUser(user) != 0) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<String> loginAcc(@RequestBody User user) {
		return new ResponseEntity<String>(user.getUsername(), HttpStatus.ACCEPTED);
	}

	@GetMapping(produces="application/json")
	public ResponseEntity<String> getUsername(@RequestBody User user){
		User exists = userService.getUser(user.getUsername());
		if(exists == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>(user.getUsername(), HttpStatus.ACCEPTED);
	}
}
