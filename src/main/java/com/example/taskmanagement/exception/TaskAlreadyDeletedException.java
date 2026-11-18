package com.example.taskmanagement.exception;

public class TaskAlreadyDeletedException extends RuntimeException{
    public TaskAlreadyDeletedException(String message) {
        super(message);
    }
}
