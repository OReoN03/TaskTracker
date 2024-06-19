package com.example.tasktracker.rest.dto;

import lombok.Data;

@Data
public class UserDto {
    private String login;
    private String firstName;
    private String patronymic;
    private String lastName;
    private String email;
}
