package com.clovirtualfashion.homework.employee.repository.jpa.entity;

import com.clovirtualfashion.homework.common.value.BaseEntity;
import com.clovirtualfashion.homework.employee.domain.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "employee")
@NoArgsConstructor
@DynamicUpdate
public class EmployeeEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "employee_name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "contact")
  private String contact;

  @Column(name = "joined_date")
  private LocalDate joinedDate;

  public static EmployeeEntity fromModel(Employee employee) {
    EmployeeEntity entity = new EmployeeEntity();
    entity.id = employee.getId();
    entity.name = employee.getName();
    entity.email = employee.getEmail();
    entity.contact = employee.getContact();
    entity.joinedDate = employee.getJoinedDate();
    return entity;
  }

  public Employee toModel() {
    return Employee.builder()
        .id(id)
        .name(name)
        .email(email)
        .contact(contact)
        .joinedDate(joinedDate)
        .build();
  }

}
