package com.clovirtualfashion.homework.employee.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Employee {

  private Long id;
  private String name;
  private String email;
  private String contact;
  private LocalDate joinedDate;

}
