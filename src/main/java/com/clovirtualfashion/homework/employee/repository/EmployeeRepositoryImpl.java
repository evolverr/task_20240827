package com.clovirtualfashion.homework.employee.repository;

import com.clovirtualfashion.homework.common.value.PageVo;
import com.clovirtualfashion.homework.common.value.pagination.PageList;
import com.clovirtualfashion.homework.common.value.pagination.Pagination;
import com.clovirtualfashion.homework.employee.domain.Employee;
import com.clovirtualfashion.homework.employee.repository.jpa.entity.EmployeeEntity;
import com.clovirtualfashion.homework.employee.repository.jpa.repository.EmployeeJpaRepository;
import com.clovirtualfashion.homework.employee.service.pod.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

  private final EmployeeJpaRepository employeeJpaRepository;

  @Override
  public PageList<Employee> getEmployeeList(PageVo pageVo) {
    Page<EmployeeEntity> employeePage = employeeJpaRepository.findAll(PageRequest.of(pageVo.page() - 1, pageVo.pageSize()));

    return PageList.<Employee>builder()
        .list(employeePage.stream().map(EmployeeEntity::toModel).toList())
        .pagination(Pagination.from(employeePage.getPageable().getPageNumber() + 1, employeePage.getPageable().getPageSize(), employeePage.getTotalElements()))
        .build();
  }

  @Override
  public List<Employee> findByName(String name) {
    return employeeJpaRepository.findByName(name)
        .stream()
        .map(EmployeeEntity::toModel)
        .collect(Collectors.toList());
  }

  @Override
  public Employee save(Employee employee) {
    return employeeJpaRepository.save(EmployeeEntity.fromModel(employee)).toModel();
  }

  @Override
  public List<Employee> saveAll(List<Employee> employees) {
    List<EmployeeEntity> employeeEntities = employees.stream().map(EmployeeEntity::fromModel).collect(Collectors.toList());
    return employeeJpaRepository.saveAll(employeeEntities)
        .stream()
        .map(EmployeeEntity::toModel)
        .collect(Collectors.toList());
  }

}
