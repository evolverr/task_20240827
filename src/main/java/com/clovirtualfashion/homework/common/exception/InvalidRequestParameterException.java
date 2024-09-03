package com.clovirtualfashion.homework.common.exception;

public class InvalidRequestParameterException extends RuntimeException {

  public InvalidRequestParameterException(String message) {
    super(message);
  }

  public InvalidRequestParameterException(Throwable throwable) {
    super(throwable);
  }

}
