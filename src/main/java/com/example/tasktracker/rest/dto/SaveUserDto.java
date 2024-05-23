package com.example.tasktracker.rest.dto;

import lombok.Data;

@Data
public class SaveUserDto {
    private String login;
    private String firstName;
    private String patronymic;
    private String lastName;
    private String password;
    private String email;
}
