package com.clovirtualfashion.homework.employee.controller.util;

import com.clovirtualfashion.homework.common.util.CustomObjectMapperUtil;
import com.clovirtualfashion.homework.employee.controller.request.EmployeeCreateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeRequestConverterTest {

  private EmployeeRequestConverter employeeRequestConverter;

  @BeforeEach
  void init() {
    CustomObjectMapperUtil customObjectMapperUtil = new CustomObjectMapperUtil(new ObjectMapper());
    this.employeeRequestConverter = new EmployeeRequestConverter(customObjectMapperUtil);
  }

  // csv 문자열 입력 시 정상 테스트
  @Test
  void success_test_csv_string_upload() {
    // given
    String content = "김클로,clo@clovf.com,010-1111-2222,2012-01-05\n" +
                     "박마블,md@clovf.com,010-3333-4444,2013-01-05\n" +
                     "홍커넥,connect@clovf.com,010-5555-6666,2014-01-05";

    // when
    List<EmployeeCreateRequest> employeeCreateRequests = employeeRequestConverter.convert(content);

    // then
    assertEquals(3, employeeCreateRequests.size());
    assertEquals("김클로", employeeCreateRequests.get(0).getName());
    assertEquals("박마블", employeeCreateRequests.get(1).getName());
    assertEquals("홍커넥", employeeCreateRequests.get(2).getName());

    assertEquals("clo@clovf.com", employeeCreateRequests.get(0).getEmail());
    assertEquals("md@clovf.com", employeeCreateRequests.get(1).getEmail());
    assertEquals("connect@clovf.com", employeeCreateRequests.get(2).getEmail());
  }

  @Test
  void success_test_csv_file_upload() throws IOException {
    // given
    String fileName = "contact.csv";
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
    String content = new String(inputStream.readAllBytes());

    // when
    List<EmployeeCreateRequest> employeeCreateRequests = employeeRequestConverter.convert(content);

    // then
    assertEquals(3, employeeCreateRequests.size());
    assertEquals("김클로", employeeCreateRequests.get(0).getName());
    assertEquals("박마블", employeeCreateRequests.get(1).getName());
    assertEquals("홍커넥", employeeCreateRequests.get(2).getName());

    assertEquals("clo@clovf.com", employeeCreateRequests.get(0).getEmail());
    assertEquals("md@clovf.com", employeeCreateRequests.get(1).getEmail());
    assertEquals("connect@clovf.com", employeeCreateRequests.get(2).getEmail());
  }

  // json 문자열 업로드 시 정상 테스트
  @Test
  void success_test_json_string_upload() {
// given
    String content = "[\n" +
                     "    {\"name\": \"김클로\", \"email\":\"clo@clovf.com\", \"tel\":\"010-1111-2222\", \"joined\":\"2012-01-05\"},\n" +
                     "    {\"name\": \"박마블\", \"email\":\"md@clovf.com\", \"tel\":\"010-3333-4444\", \"joined\":\"2013-01-05\"},\n" +
                     "    {\"name\": \"홍커넥\", \"email\":\"connect@clovf.com\", \"tel\":\"010-5555-6666\", \"joined\":\"2014-01-05\"}\n" +
                     "]";

    // when
    List<EmployeeCreateRequest> employeeCreateRequests = employeeRequestConverter.convert(content);

    // then
    assertEquals(3, employeeCreateRequests.size());
    assertEquals("김클로", employeeCreateRequests.get(0).getName());
    assertEquals("박마블", employeeCreateRequests.get(1).getName());
    assertEquals("홍커넥", employeeCreateRequests.get(2).getName());

    assertEquals("clo@clovf.com", employeeCreateRequests.get(0).getEmail());
    assertEquals("md@clovf.com", employeeCreateRequests.get(1).getEmail());
    assertEquals("connect@clovf.com", employeeCreateRequests.get(2).getEmail());
  }
  // json 파일 업로드 시 정상 테스트
  @Test
  void success_test_json_file_upload() throws IOException {
    // given
    String fileName = "contact.json";
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
    String content = new String(inputStream.readAllBytes());

    // when
    List<EmployeeCreateRequest> employeeCreateRequests = employeeRequestConverter.convert(content);

    // then
    assertEquals(3, employeeCreateRequests.size());
    assertEquals("김클로", employeeCreateRequests.get(0).getName());
    assertEquals("박마블", employeeCreateRequests.get(1).getName());
    assertEquals("홍커넥", employeeCreateRequests.get(2).getName());

    assertEquals("clo@clovf.com", employeeCreateRequests.get(0).getEmail());
    assertEquals("md@clovf.com", employeeCreateRequests.get(1).getEmail());
    assertEquals("connect@clovf.com", employeeCreateRequests.get(2).getEmail());
  }

}