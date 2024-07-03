package com.oscell.oscell.config.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404Handler() {return ResponseEntity.notFound().build();}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400Handler(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationError::new).toList());
    }

    private record DataValidationError(String field, String message) {
        public DataValidationError(FieldError error) {this(error.getField(), error.getDefaultMessage());}
    }
}
