package com.hcl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.dao.AuthorityDAO;
import com.hcl.dao.UserDAO;
import com.hcl.model.Authority;
import com.hcl.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	@Autowired
	AuthorityDAO authDAO;

	@Override
	// Creates a user return 0 if successful, and 1 or 2 as error codes
	public int createUser(User user) {
		// Checks if the username already exists
		if (userDAO.getUser(user.getUsername()) != null) {
			return 1; // If it does returns 1
		}
		// Checks if username or password is blank or too long
		if (user.getUsername().length() == 0 
				|| user.getPassword().length() == 0 
				|| user.getUsername().length() > 50
				|| user.getPassword().length() > 50) {
			return 2; // If so returns 2
		}
		// Encodes the password
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		// Changes user password to the encoded password
		user.setPassword(b.encode(user.getPassword()));
		// Then calls the DAO to create a user and its authorities
		userDAO.createUser(user);
		// Currently creates all users as ROLE_USER
		authDAO.createAuthority(new Authority(user.getUsername(), "ROLE_USER"));
		return 0;
	}

	@Override
	// Gets user given a username
	public User getUser(String userName) {
		return userDAO.getUser(userName);
	}

}
