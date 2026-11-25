package com.example.taskmanagement.exception;

public class InvalidUserModificationException extends RuntimeException{
    public InvalidUserModificationException(String message) {
        super(message);
    }
}
