package com.example.restapidevelopment.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public Employee addEmployee(Employee employee) {
    if(employeeRepository.existsById(employee.getID())) {
      throw new IllegalStateException(employee.getID() +" already exist");
    }
    employeeRepository.save(employee);
    return employee;
  }

  public List<Employee> getAllEmployees() {
    List<Employee> employees = List.of(Employee.builder().setID(1).setFirstName("ravii").setEmail("dummy1@gmail.com").build(),
      Employee.builder().setID(2).setFirstName("abay").setEmail("dummy2@gmail.com").build());

    employeeRepository.saveAll(employees);
    return employeeRepository.findAll();
  }

  public Employee getEmployee(int id) {

    Optional<Employee> byId = employeeRepository.findById(id);
    if(!byId.isPresent()) {
      System.out.println("No Employee exists with ID : " +id);
    }
    return byId.get();
  }

  public String deleteEmployee(int id) {
    if(!employeeRepository.existsById(id)) {
      throw new IllegalStateException(id +" not exist");
    }
    employeeRepository.deleteById(id);
    return "success";
  }

  public Employee editEmployee(Employee employee, int id) {
    if(!employeeRepository.existsById(id)) {
      throw new IllegalStateException(id +" not exist");
    }
    employeeRepository.deleteById(id);
    employeeRepository.save(employee);

    return employee;
  }
}
