package com.example.restapidevelopment.employee.repository;

import com.example.restapidevelopment.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
