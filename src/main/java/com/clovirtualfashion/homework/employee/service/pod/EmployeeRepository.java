package com.clovirtualfashion.homework.employee.service.pod;

import com.clovirtualfashion.homework.common.value.PageVo;
import com.clovirtualfashion.homework.common.value.pagination.PageList;
import com.clovirtualfashion.homework.employee.domain.Employee;

import java.util.List;

public interface EmployeeRepository {

  PageList<Employee> getEmployeeList(PageVo pageVo);

  List<Employee> findByName(String name);

  Employee save(Employee employee);

  List<Employee> saveAll(List<Employee> employee);

}
