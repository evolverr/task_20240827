package com.clovirtualfashion.homework.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomStringUtilTest {

  @Test
  void success_convert_phone_number_format() {
    // given
    String input = "01011112222";
    String expected = "010-1111-2222";

    // when
    String result = CustomStringUtil.convertPhoneNumberFormat(input);

    // then
    assertEquals(expected, result);
  }

  @Test
  void validate_phone_number_format() {
    // given
    String input = "01011112222";
    // when
    // then
    assertTrue(CustomStringUtil.isValidPhoneNumber(input));
  }

  @Test
  void invalidate_phone_number_format() {
    // given
    String input = "0101111";
    // when
    // then
    assertFalse(CustomStringUtil.isValidPhoneNumber(input));
  }

  @Test
  void validate_email_format() {
    // given
    String input = "clo@clo.com";
    // when
    // then
    assertTrue(CustomStringUtil.isValidEmail(input));
  }

  @Test
  void invalidate_email_format() {
    // given
    String input = "clo@clocom";
    // when
    // then
    assertFalse(CustomStringUtil.isValidEmail(input));
  }
}