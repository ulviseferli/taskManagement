package com.example.taskmanagement.exception;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
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
        response.put("path", request.getRequestURI());
        response.put("message", ex.getMessage());
        response.put("status", 500);
        response.put("timestamp", LocalDateTime.now());

        return response;
    }
}
