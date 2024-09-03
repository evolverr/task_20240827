package com.clovirtualfashion.homework.employee.controller;

import com.clovirtualfashion.homework.common.value.ApiResponseResource;
import com.clovirtualfashion.homework.common.value.PageVo;
import com.clovirtualfashion.homework.employee.controller.request.EmployeeCreateRequestForm;
import com.clovirtualfashion.homework.employee.controller.response.EmployeeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Employee", description = "직원 연락 정보 조회 API")
public interface EmployeeApi {

  @Operation(summary = "직원 기본 연락 정보 리스트 조회 with Page")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "직원 기본 연락 정보 리스트 성공")
  })
  ResponseEntity<ApiResponseResource<List<EmployeeResponse>>> getEmployeeContactList(PageVo pageVo);

  @Operation(summary = "직원의 기본 연락 정보 조회 by name", description = "파라미터로 입력된 직원명과 일치한 사용자 기본 연락 정보를 조회한다. 동명이인이 존재할 수 있기때문에 List 로 반환한다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "직원 기본 연락 정보 성공"),
      @ApiResponse(responseCode = "404", description = "직원 기본 연락 정보 실패")
  })
  ResponseEntity<ApiResponseResource<List<EmployeeResponse>>> getEmployeeContact(String name);

  @Operation(
      summary = "직원 기본 연락 정보 저장",
      description = "csv, json 포멧의 문자열 || 파일을 기준으로 입력한 유저 정보를 저장합니다.",
      requestBody = @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
      schema = @Schema(implementation = EmployeeCreateRequestForm.class)))
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "유저 정보 저장 성공"),
      @ApiResponse(responseCode = "400", description = "유저 정보 저장 실패")
  })
  ResponseEntity<ApiResponseResource<EmployeeResponse>> saveEmployeeContactsWithJson(EmployeeCreateRequestForm employeeCreateRequestForm);

}
