package com.example.tasktracker.rest.dto;

import lombok.Data;

@Data
public class BoardDto {
    private String title;
    private String description;
    private Integer workspaceId;
}
