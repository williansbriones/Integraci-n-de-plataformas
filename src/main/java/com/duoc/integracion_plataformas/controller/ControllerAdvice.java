package com.duoc.integracion_plataformas.controller;


import java.time.LocalDate;

import com.duoc.integracion_plataformas.dto.ErrorDto;
import com.duoc.integracion_plataformas.exeption.FlowException;
import com.duoc.integracion_plataformas.exeption.InternalException;
import com.duoc.integracion_plataformas.exeption.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

  private final ErrorDto errorDto = new ErrorDto("500", "error", null);

  @ExceptionHandler(value = UserException.class)
  public ResponseEntity<ErrorDto> UserExceptionHandler(UserException e) {
    return ResponseEntity.status(e.getRequest())
        .body(new ErrorDto(e.getCode(), e.getMessage(), e.getDate()));
  }

  @ExceptionHandler(value = FlowException.class)
  public ResponseEntity<ErrorDto> FlowExceptionHandler(FlowException flowException) {
    return ResponseEntity.status(flowException.getHttpStatusCode())
        .body(new ErrorDto(flowException.getCode(), flowException.getMessage(), LocalDate.now()));
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDto> methodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    log.error("Error message {} \n {}", e.getMessage(), e.getCause(), e);
    log.warn("Error message {} \n {}", e.getMessage(), e.getCause(), e);
    log.info("Error message {} \n {}", e.getMessage(), e.getCause(), e);
    return ResponseEntity.status(500).body(new ErrorDto("500", e.getMessage(), null));
  }

  @ExceptionHandler(value = InternalException.class)
  public ResponseEntity<ErrorDto> InternalException(Exception e) {
    log.error("Error message {} \n {}", e.getMessage(), e.getCause(), e);
    return ResponseEntity.status(500).body(this.errorDto);
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorDto> ExceptionHandler(Exception e) {
    log.error("Error message {} \n {}", e.getMessage(), e.getCause(), e);
    return ResponseEntity.status(500).body(this.errorDto);
  }
}
