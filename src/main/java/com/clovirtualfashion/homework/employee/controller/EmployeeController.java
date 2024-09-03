package com.clovirtualfashion.homework.employee.controller;

import com.clovirtualfashion.homework.common.exception.InvalidRequestParameterException;
import com.clovirtualfashion.homework.common.value.ApiResponseResource;
import com.clovirtualfashion.homework.common.value.PageVo;
import com.clovirtualfashion.homework.common.value.pagination.PageList;
import com.clovirtualfashion.homework.employee.controller.request.EmployeeCreateRequest;
import com.clovirtualfashion.homework.employee.controller.request.EmployeeCreateRequestForm;
import com.clovirtualfashion.homework.employee.controller.response.EmployeeResponse;
import com.clovirtualfashion.homework.employee.controller.util.EmployeeRequestConverter;
import com.clovirtualfashion.homework.employee.domain.Employee;
import com.clovirtualfashion.homework.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController implements EmployeeApi {

  private final EmployeeRequestConverter employeeRequestConverter;
  private final EmployeeService employeeService;

  /**
   * 직원 기본 연락 정보 리스트 조회 with Page
   */
  @Override
  @GetMapping("/")
  public ResponseEntity<ApiResponseResource<List<EmployeeResponse>>> getEmployeeContactList(PageVo pageVo) {

    PageList<Employee> employees = employeeService.getEmployees(pageVo);

    ApiResponseResource<List<EmployeeResponse>> result = ApiResponseResource.<List<EmployeeResponse>>builder()
        .data(employees.list().stream().map(EmployeeResponse::fromDomain).toList())
        .pagination(employees.pagination())
        .build();

    return ResponseEntity.ok(result);
  }

  /**
   * 직원의 기본 연락 정보 조회 by name
   */
  @Override
  @GetMapping("/{name}")
  public ResponseEntity<ApiResponseResource<List<EmployeeResponse>>> getEmployeeContact(@PathVariable String name) {
    List<EmployeeResponse> response = employeeService.getEmployeeByName(name)
        .stream()
        .map(EmployeeResponse::fromDomain)
        .toList();

    ApiResponseResource<List<EmployeeResponse>> result = ApiResponseResource.<List<EmployeeResponse>>builder()
        .data(response)
        .build();

    return ResponseEntity.ok(result);
  }

  /**
   * 직원 기본 연락 정보 저장
   */
  @Override
  @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ApiResponseResource<EmployeeResponse>> saveEmployeeContactsWithJson(@ModelAttribute EmployeeCreateRequestForm employeeCreateRequestForm) {

    List<EmployeeCreateRequest> employeeCreateRequests = convertFromForm(employeeCreateRequestForm);
    employeeCreateRequests.forEach(EmployeeCreateRequest::validate);

    List<Employee> employee = toDomain(employeeCreateRequests);

    List<EmployeeResponse> employeeResponses = employeeService.saveAll(employee)
        .stream()
        .map(EmployeeResponse::fromDomain)
        .toList();

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{name}")
        .buildAndExpand(employeeResponses.stream().map(EmployeeResponse::getName).toList())
        .toUri();

    return ResponseEntity.created(location).build();
  }

  private List<EmployeeCreateRequest> convertFromForm(EmployeeCreateRequestForm requestForm) {
    List<EmployeeCreateRequest> employeeRequests = new ArrayList<>();

    try {
      if (StringUtils.hasText(requestForm.content())) {
        employeeRequests.addAll(employeeRequestConverter.convert(requestForm.content()));
      }
      if (requestForm.file() != null) {
        String content = new String(requestForm.file().getBytes());
        employeeRequests.addAll(employeeRequestConverter.convert(content));
      }
      return employeeRequests;
    } catch (Exception e) {
      throw new InvalidRequestParameterException(e);
    }
  }

  private List<Employee> toDomain(List<EmployeeCreateRequest> employeeRequests) {
    return employeeRequests.stream().map(EmployeeCreateRequest::toDomain).toList();
  }

}
