package com.clovirtualfashion.homework.employee.controller.request;

import com.clovirtualfashion.homework.common.exception.InvalidRequestParameterException;
import com.clovirtualfashion.homework.common.util.CustomDateTimeUtil;
import com.clovirtualfashion.homework.common.util.CustomStringUtil;
import com.clovirtualfashion.homework.employee.domain.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateRequest {

  @JsonProperty(value = "name")
  @CsvBindByPosition(position = 0)
  private String name;

  @JsonProperty(value = "email")
  @CsvBindByPosition(position = 1)
  private String email;

  @JsonProperty(value = "tel")
  @CsvBindByPosition(position = 2)
  private String contact;

  @JsonProperty(value = "joined")
  @CsvBindByPosition(position = 3)
  private String joinedDate;

  public Employee toDomain() {
    return Employee.builder()
        .name(name)
        .email(email)
        .contact(CustomStringUtil.convertPhoneNumberFormat(contact))
        .joinedDate(CustomDateTimeUtil.convertDateFormat(joinedDate))
        .build();
  }

  public void validate() {
    if (!StringUtils.hasText(name)) {
      throw new InvalidRequestParameterException(String.format("name is invalid. name=[%s]", name));
    }

    if (!StringUtils.hasText(email) || !CustomStringUtil.isValidEmail(email)) {
      throw new InvalidRequestParameterException(String.format("email is invalid. email=[%s]", email));
    }

    if (!StringUtils.hasText(contact) || !CustomStringUtil.isValidPhoneNumber(contact)) {
      throw new InvalidRequestParameterException(String.format("tel is invalid. tel=[%s]", contact));
    }

    if (!StringUtils.hasText(joinedDate) || !CustomDateTimeUtil.isValidDateFormat(joinedDate)) {
      throw new InvalidRequestParameterException(String.format("joinedDate is invalid. joinedDate=[%s]", joinedDate));
    }
  }

}
