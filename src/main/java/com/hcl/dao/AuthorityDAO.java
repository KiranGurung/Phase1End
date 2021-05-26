package com.hcl.dao;

import com.hcl.model.Authority;

public interface AuthorityDAO {
	void createAuthority(Authority auth);
	Authority getAuth(String userName);
}
