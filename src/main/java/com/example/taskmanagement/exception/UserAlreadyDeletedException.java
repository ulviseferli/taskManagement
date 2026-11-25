package com.example.taskmanagement.exception;

public class UserAlreadyDeletedException extends RuntimeException{
    public UserAlreadyDeletedException(String message) {
        super(message);
    }
}
