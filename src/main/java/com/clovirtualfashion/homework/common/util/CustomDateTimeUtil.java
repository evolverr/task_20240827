package com.clovirtualfashion.homework.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CustomDateTimeUtil {

  // 지원하는 날짜 포맷 리스트
  private static final List<DateTimeFormatter> dateFormats = List.of(
      DateTimeFormatter.ofPattern("yyyy-MM-dd"),
      DateTimeFormatter.ofPattern("yyyy.MM.dd"),
      DateTimeFormatter.ofPattern("yyyyMMdd")
  );

  /**
   * 날짜 형식 유효성 검사
   */
  public static boolean isValidDateFormat(String dateString) {
    for (DateTimeFormatter formatter : dateFormats) {
      try {
        LocalDate.parse(dateString, formatter);
        return true; // 성공적으로 파싱되면 true 반환
      } catch (DateTimeParseException e) {
        // 포맷에 맞지 않는 경우 다음 포맷 시도
      }
    }

    return false;
  }

  public static LocalDate convertDateFormat(String joinedDate) {
    return LocalDate.parse(joinedDate);
  }
}
