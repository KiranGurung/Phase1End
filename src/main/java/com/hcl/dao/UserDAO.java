package com.hcl.dao;

import com.hcl.model.User;

public interface UserDAO {
	
	public void createUser(User user);
	public User getUser(String userName);
}
