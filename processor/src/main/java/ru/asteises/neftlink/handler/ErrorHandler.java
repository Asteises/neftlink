package ru.asteises.neftlink.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.asteises.neftlink.handler.exception.BaseNotFound;
import ru.asteises.neftlink.handler.exception.UserNotFound;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({UserNotFound.class, BaseNotFound.class})
    public ResponseEntity<String> notFoundHandler(final RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
