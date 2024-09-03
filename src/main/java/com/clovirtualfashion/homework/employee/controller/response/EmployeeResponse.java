package com.clovirtualfashion.homework.employee.controller.response;

import com.clovirtualfashion.homework.employee.domain.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EmployeeResponse {

  @Schema(name = "id", description = "직원 id", example = "1")
  private Long id;

  @Schema(name = "name", description = "직원명", example = "김클로")
  private String name;

  @Schema(name = "email", description = "이메일", example = "clo@clovf.com")
  private String email;

  @Schema(name = "tel", description = "연락처", example = "010-1111-2222")
  private String tel;

  @Schema(name = "joined", description = "입사일", example = "2024-09-01")
  private LocalDate joined;

  public static EmployeeResponse fromDomain(Employee employee) {
    return EmployeeResponse.builder()
        .id(employee.getId())
        .name(employee.getName())
        .email(employee.getEmail())
        .tel(employee.getContact())
        .joined(employee.getJoinedDate())
        .build();
  }

}
