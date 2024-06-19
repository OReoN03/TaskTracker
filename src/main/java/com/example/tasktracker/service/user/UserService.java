package com.example.tasktracker.service.user;

import com.example.tasktracker.rest.dto.SaveUserDto;
import com.example.tasktracker.rest.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto createUser(SaveUserDto user);

    UserDto findUserById(Integer id);

    void updateUser(int id, SaveUserDto saveUserDto);

    void deleteUser(Integer id);
}
