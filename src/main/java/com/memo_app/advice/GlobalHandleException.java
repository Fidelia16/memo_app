package com.memo_app.advice;

import com.memo_app.exception.HttpConflict;
import com.memo_app.model.wrapper.MemoWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandleException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();
        e.getFieldErrors().forEach((error)->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                MemoWrapper.builder()
                        .message("BAD REQUEST")
                        .code(HttpStatus.BAD_REQUEST.value())
                        .data(errors)
                        .build()
        );
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                MemoWrapper.builder()
                        .message("NOT FOUND")
                        .code(HttpStatus.NOT_FOUND.value())
                        .build()
        );
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                MemoWrapper.builder()
                        .message("METHOD_NOT_ALLOWED")
                        .code(HttpStatus.METHOD_NOT_ALLOWED.value()).build()
        );
    }
    @ExceptionHandler(HttpConflict.class)
    public ResponseEntity<?> handleHttpConflict(HttpConflict e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                MemoWrapper.builder()
                        .message(e.getMessage())
                        .code(HttpStatus.CONFLICT.value())
                        .build()
        );
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException e
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                MemoWrapper.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("INVALID PARAMETER: " + e.getName())
                        .build()
        );
    }
}
