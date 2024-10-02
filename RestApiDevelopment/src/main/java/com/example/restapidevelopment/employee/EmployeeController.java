package com.example.restapidevelopment.employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
  @GetMapping(path = "/home")
  public String Home() {
    return "Welcome to Springboot Rest Api Development";
  }
}
