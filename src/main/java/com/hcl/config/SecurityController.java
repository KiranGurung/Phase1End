package com.hcl.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityController extends WebSecurityConfigurerAdapter {
	// Sets up datasource, MYSQL currently
	@Autowired
	DataSource ds;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Sets up the authentication using jdbc and the datasource
		auth.jdbcAuthentication().dataSource(ds);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// Encodes password using BCryptPassword
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Allows any user to go to the create users page
		// At "/" and "/create"
		// Allows any user to go to the "/login" page also
		// Only allows users with roles USER and ADMIN to go to "/user" page
		// Also sets default page after successful login to "/user"
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/create").permitAll()
				.antMatchers("/user").hasAnyRole("USER", "ADMIN")
				.and()
				.formLogin()
				.loginPage("/login").permitAll()
                .defaultSuccessUrl("/user", true)
				.and().logout().permitAll()
				.logoutSuccessUrl("/login?logout");
		http.csrf().disable();
	}
}
