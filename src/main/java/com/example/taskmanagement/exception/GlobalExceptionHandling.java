package com.example.taskmanagement.exception;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandling {
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> handleTaskNotFoundException(TaskNotFoundException ex, HttpServletRequest request){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("path",request.getRequestURI());
        response.put("message",ex.getMessage());
        response.put("status",404);
        response.put("timestamp", LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(TaskAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleTaskAlreadyExistsException(
            TaskAlreadyExistsException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("path", request.getRequestURI());
        response.put("message", ex.getMessage());
        response.put("status", 409);
        response.put("timestamp", LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(TaskAlreadyDeletedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleTaskAlreadyDeletedException(
            TaskAlreadyDeletedException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("path", request.getRequestURI());
        response.put("message", ex.getMessage());
        response.put("status", 409);
        response.put("timestamp", LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(InvalidTaskModificationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public Map<String, Object> handleInvalidTaskModificationException(
            InvalidTaskModificationException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("path", request.getRequestURI());
        response.put("message", ex.getMessage());
        response.put("status", 422);
        response.put("timestamp", LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUnexpected(
            Exception ex,
            HttpServletRequest request
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        log.error("Internal server error, path{}",request.getRequestURI(),ex);
        response.put("path", request.getRequestURI());
        response.put("message", "Internal server error");
        response.put("status", 500);
        response.put("timestamp", LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        log.error("Data integrity violation path{}",request.getRequestURI(),ex);
        response.put("path", request.getRequestURI());
        response.put("message", "Data integrity violation");
        response.put("status", 409);
        response.put("timestamp", LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        log.warn("Malformed request body, path{}",request.getRequestURI());
        response.put("path", request.getRequestURI());
        response.put("message", "Body was malformed or not readable");
        response.put("status", 400);
        response.put("timestamp", LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        log.warn("Method argument type error, path{}",request.getRequestURI());
        response.put("path", request.getRequestURI());
        response.put("message", "Method argument type error");
        response.put("status", 400);
        response.put("timestamp", LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, Object> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.putIfAbsent(
                        error.getField(),
                        error.getDefaultMessage()
                ));

        log.warn("Validation error, path{}, errors{}",request.getRequestURI(),errors);

        response.put("path", request.getRequestURI());
        response.put("message", "Validation error");
        response.put("status", 400);
        response.put("errors", errors);
        response.put("timestamp", LocalDateTime.now());

        return response;
    }
}
