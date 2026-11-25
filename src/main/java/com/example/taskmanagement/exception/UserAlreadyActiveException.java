package com.example.taskmanagement.exception;

public class UserAlreadyActiveException extends RuntimeException{
    public UserAlreadyActiveException(String message) {
        super(message);
    }
}
