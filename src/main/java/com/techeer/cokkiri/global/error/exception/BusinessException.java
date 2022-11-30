package com.techeer.cokkiri.global.error.exception;

import com.techeer.cokkiri.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
  private final ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
