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
	public int createUser(User user) {
		// TODO Auto-generated method stub
		if(userDAO.getUser(user.getUsername()) != null) {
			return 1;
		}
		if(user.getUsername().length() == 0 || user.getPassword().length() == 0) {
			return 2;
		}
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		user.setPassword(b.encode(user.getPassword()));
		userDAO.createUser(user);
		authDAO.createAuthority(new Authority(user.getUsername(), "ROLE_USER"));
		return 0;
	}

	@Override
	public User getUser(String userName) {
		// TODO Auto-generated method stub
		return userDAO.getUser(userName);
	}

}
