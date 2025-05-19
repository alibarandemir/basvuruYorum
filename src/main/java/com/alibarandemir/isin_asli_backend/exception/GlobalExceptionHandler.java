package com.alibarandemir.isin_asli_backend.exception;

import com.alibarandemir.isin_asli_backend.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<String>> handleException(Exception ex) {
        ex.printStackTrace(); // log için
        return ResponseEntity.badRequest().body(ResponseDto.fail(ex.getMessage()));
    }
}
