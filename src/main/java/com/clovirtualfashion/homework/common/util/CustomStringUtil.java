package com.clovirtualfashion.homework.common.util;

import java.util.regex.Pattern;

public class CustomStringUtil {

  // 전화번호 패턴 정의
  private static final String PHONE_REGEX = "^(\\d{3}-\\d{4}-\\d{4})|(\\d{3}-\\d{3}-\\d{4})|(\\d{11})|(\\d{10})$";
  private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

  public static String convertPhoneNumberFormat(String phoneNumber) {
    String phoneNumberRegEx = "(\\d{3})(\\d{3,4})(\\d{4})";
    return phoneNumber.replaceAll(phoneNumberRegEx, "$1-$2-$3");
  }
  /**
   * 전화번호 유효성 검사
   */
  public static boolean isValidPhoneNumber(String phoneNumber) {
//    String phoneNumberRegEx = "(\\d{3})(\\d{3,4})(\\d{4})";
    return phoneNumber.matches(PHONE_REGEX);
  }
  /**
   * 이메일 유효성 검사
   */
  public static boolean isValidEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    return email.matches(emailRegex);
  }

}
