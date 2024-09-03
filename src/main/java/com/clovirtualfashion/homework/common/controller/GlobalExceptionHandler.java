package com.clovirtualfashion.homework.common.controller;

import com.clovirtualfashion.homework.common.exception.FailConvertWithObjectMapperException;
import com.clovirtualfashion.homework.common.exception.InvalidRequestParameterException;
import com.clovirtualfashion.homework.common.value.ApiResponseResource;
import com.clovirtualfashion.homework.common.value.StatusResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(FailConvertWithObjectMapperException.class)
  public ResponseEntity<ApiResponseResource<?>> handleInvalidParameterException(final FailConvertWithObjectMapperException exception) {
    log.error("Error when convert with object mapper.", exception);
    StatusResult result = StatusResult.badRequestErrorResult(exception.getMessage());
    return new ResponseEntity<>(ApiResponseResource.error(result), HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(InvalidRequestParameterException.class)
  public ResponseEntity<ApiResponseResource<?>> handleInvalidRequestParameterException(final InvalidRequestParameterException exception) {
    log.error("Error with InvalidRequestParameterException.", exception);
    StatusResult result = StatusResult.badRequestErrorResult(exception.getMessage());
    return new ResponseEntity<>(ApiResponseResource.error(result), HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApiResponseResource<?>> handleRuntimeException(RuntimeException exception) {
    log.error("Occur unknown error.", exception);
    StatusResult result = StatusResult.internalServerErrorResult(exception.getMessage());
    return new ResponseEntity<>(ApiResponseResource.error(result), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
