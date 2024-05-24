package com.example.tasktracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Task already closed")
public class TaskAlreadyClosedException extends Exception {
    public TaskAlreadyClosedException(String message) {
        super(message);
    }
}
