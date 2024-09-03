package com.clovirtualfashion.homework.common.value;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record StatusResult (
    int status,
    String message,
    String subMessage,
    String code
) {

  public static StatusResult successResult() {
    return StatusResult.builder()
        .status(HttpStatus.OK.value())
        .build();
  }

  public static StatusResult badRequestErrorResult(String message){
    return StatusResult.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .message(message)
        .build();
  }

  public static StatusResult internalServerErrorResult(String message){
    return StatusResult.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(message)
        .build();
  }

}
