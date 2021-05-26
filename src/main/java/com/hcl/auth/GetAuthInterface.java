package com.hcl.auth;

import org.springframework.security.core.Authentication;

// Interface for getting auth details
public interface GetAuthInterface {
	Authentication getAuth();
}
