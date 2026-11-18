package com.example.taskmanagement.exception;

public class InvalidTaskModificationException extends RuntimeException{
    public InvalidTaskModificationException(String message) {
        super(message);
    }
}
