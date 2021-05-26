package com.hcl.dao;

import com.hcl.model.Authority;

// DAO for accessing authorities table
public interface AuthorityDAO {
	void createAuthority(Authority auth);
	Authority getAuth(String userName);
}
