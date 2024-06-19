package com.example.tasktracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Task already in first list")
public class TaskAlreadyInFirstListException extends RuntimeException {
    public TaskAlreadyInFirstListException(String message) {
        super(message);
    }
}
