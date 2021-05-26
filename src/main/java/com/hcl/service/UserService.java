package com.hcl.service;

import com.hcl.model.User;

// User service for creating account
public interface UserService {

	public int createUser(User user);
	public User getUser(String userName);
}
