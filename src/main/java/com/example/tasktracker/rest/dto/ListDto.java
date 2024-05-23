package com.example.tasktracker.rest.dto;

import lombok.Data;

@Data
public class ListDto {
    private Integer id;
    private String title;
    private Integer boardId;
}
