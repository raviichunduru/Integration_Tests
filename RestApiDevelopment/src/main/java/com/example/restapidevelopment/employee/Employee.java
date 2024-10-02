package com.example.restapidevelopment.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder(setterPrefix = "set")
@Entity
public class Employee {

  @Id
  private Integer ID;

  private String FirstName;
  private String Email;
}
