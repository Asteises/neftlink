package ru.asteises.neftlink.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler()
    public ResponseEntity<String> runtimeHandler(final RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
}
