package com.hcl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Model for authorities table, tracks username and authority
public class Authority {
	private String username;
	private String authority;
}
