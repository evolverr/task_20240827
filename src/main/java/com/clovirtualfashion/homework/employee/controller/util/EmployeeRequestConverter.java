package com.clovirtualfashion.homework.employee.controller.util;

import com.clovirtualfashion.homework.common.exception.FailConvertWithObjectMapperException;
import com.clovirtualfashion.homework.common.util.CustomObjectMapperUtil;
import com.clovirtualfashion.homework.employee.controller.request.EmployeeCreateRequest;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeRequestConverter {

  private final CustomObjectMapperUtil customObjectMapperUtil;

  public List<EmployeeCreateRequest> convert(String content) {
    try {
      return customObjectMapperUtil.toList(content, EmployeeCreateRequest.class);
    } catch (FailConvertWithObjectMapperException e) {
      // json 을 컨버팅할 때 exception 발생했다면 csv 타입으로 다시 컨버팅 진행.
      return convertFromCsv(content);
    }
  }

  private List<EmployeeCreateRequest> convertFromCsv(String csvContent) {
    return new CsvToBeanBuilder<EmployeeCreateRequest>(new StringReader(csvContent))
        .withType(EmployeeCreateRequest.class)
        .withIgnoreLeadingWhiteSpace(true)
        .build()
        .parse();
  }

}
