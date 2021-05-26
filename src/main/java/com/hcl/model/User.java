package com.hcl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Model for users table, only tracks username and password
public class User {
	private String username;
	private String password;
}
