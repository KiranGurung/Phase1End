package com.hcl.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface GetAuthInterface {
	Authentication getAuth();
}
