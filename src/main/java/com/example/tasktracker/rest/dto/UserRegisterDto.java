package com.example.tasktracker.rest.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String login;
    private String firstName;
    private String patronymic;
    private String lastName;
    private String hashPassword;
    private String email;
}
