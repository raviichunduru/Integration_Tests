package com.example.restapidevelopment.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping(path = "/employees")
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @GetMapping(path = "/employees/{id}")
  public Employee getEmployee(@PathVariable int id ) {
    return employeeService.getEmployee(id);
  }

  @PostMapping(path = "/employees")
  public Employee addEmployee(@RequestBody Employee employee) {
    return employeeService.addEmployee(employee);
  }

  @DeleteMapping(path = "/employees/{id}")
  public String deleteEmployee(@PathVariable int id) {
    return employeeService.deleteEmployee(id);
  }

  @PutMapping(path = "/employees/{id}")
  public Employee editEmployee(@RequestBody Employee employee, @PathVariable int id) {
    return employeeService.editEmployee(employee,id);
  }


}
