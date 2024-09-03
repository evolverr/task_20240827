package com.clovirtualfashion.homework.employee.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

public record EmployeeCreateRequestForm (
  @Schema(description = "csv, json format 의 string 값을 입력해주세요.", type = "object", format = "textarea", defaultValue = "")
  String content,

  @Schema(description = "csv, json 파일을 업로드해주세요.", type = "file")
  MultipartFile file
) {

}
