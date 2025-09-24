package com.example.astronautscheduler.exception;

public class TaskConflictException extends Exception {
    public TaskConflictException(String message) {
        super(message);
    }
}
