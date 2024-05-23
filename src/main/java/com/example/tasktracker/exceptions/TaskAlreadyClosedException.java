package com.example.tasktracker.exceptions;

public class TaskAlreadyClosedException extends Exception {
    public TaskAlreadyClosedException(String message) {
        super(message);
    }
}
