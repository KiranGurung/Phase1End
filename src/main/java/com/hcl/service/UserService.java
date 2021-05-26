package com.hcl.service;

import com.hcl.model.User;

public interface UserService {

	public int createUser(User user);
	public User getUser(String userName);
}
