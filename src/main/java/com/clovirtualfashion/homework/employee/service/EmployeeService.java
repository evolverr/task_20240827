package com.clovirtualfashion.homework.employee.service;

import com.clovirtualfashion.homework.common.value.PageVo;
import com.clovirtualfashion.homework.common.value.pagination.PageList;
import com.clovirtualfashion.homework.employee.domain.Employee;
import com.clovirtualfashion.homework.employee.service.pod.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  public PageList<Employee> getEmployees(PageVo pageVo) {
    return employeeRepository.getEmployeeList(pageVo);
  }

  public List<Employee> getEmployeeByName(String name) {
    return employeeRepository.findByName(name);
  }

  @Transactional
  public List<Employee> saveAll(List<Employee> employee) {
    return employeeRepository.saveAll(employee);
  }

}
