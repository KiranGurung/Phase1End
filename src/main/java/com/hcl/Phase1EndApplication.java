package com.hcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Phase1EndApplication {

	public static void main(String[] args) {
		SpringApplication.run(Phase1EndApplication.class, args);
	}

}
