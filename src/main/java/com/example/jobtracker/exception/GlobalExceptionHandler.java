package com.example.jobtracker.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {

        var errors = ex.getBindingResult().getFieldErrors();

        Map<String, String> errorMessages = new HashMap<>();
        for (FieldError error : errors) {
            errorMessages.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errorMessages);
    }
}
