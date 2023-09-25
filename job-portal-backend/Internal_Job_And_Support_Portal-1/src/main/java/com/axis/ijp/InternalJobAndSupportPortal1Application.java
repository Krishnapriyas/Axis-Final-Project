package com.axis.ijp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.axis.ijp")
public class InternalJobAndSupportPortal1Application {

	public static void main(String[] args) {
		SpringApplication.run(InternalJobAndSupportPortal1Application.class, args);
	}

}
