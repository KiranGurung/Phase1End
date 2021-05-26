package com.hcl.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityController extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource ds;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(ds);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

//		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/create").permitAll()
				.antMatchers("/user").hasAnyRole("USER", "ADMIN")
				.and()
				.formLogin()
				.loginPage("/login").permitAll()
                .defaultSuccessUrl("/user", true)
				.and().logout().permitAll();
		http.csrf().disable();
	}
}
