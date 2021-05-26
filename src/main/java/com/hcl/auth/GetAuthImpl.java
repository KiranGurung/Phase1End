package com.hcl.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetAuthImpl implements GetAuthInterface {

	@Override
	// Returns the authentication so you can check details like username and auth roles
	public Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
