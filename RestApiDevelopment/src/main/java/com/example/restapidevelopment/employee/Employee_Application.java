package com.example.restapidevelopment.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Employee_Application
{
	public static void main(String[] args) {
		SpringApplication.run(Employee_Application.class, args);
	}


}
