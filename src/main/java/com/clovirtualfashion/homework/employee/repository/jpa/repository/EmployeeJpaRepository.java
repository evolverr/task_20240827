package com.clovirtualfashion.homework.employee.repository.jpa.repository;

import com.clovirtualfashion.homework.employee.repository.jpa.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long> {

  List<EmployeeEntity> findByName(String name);

}
