package com.example.tasktracker.rest.dto;

import lombok.Data;

@Data
public class TaskDto {
    private String title;
    private String description;
    private String label;
    private Integer listId;
}
