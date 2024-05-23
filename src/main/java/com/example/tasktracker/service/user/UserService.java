package com.example.tasktracker.service.user;

import com.example.tasktracker.model.User;
import com.example.tasktracker.rest.dto.UserLoginDto;
import com.example.tasktracker.rest.dto.SaveUserDto;
import com.example.tasktracker.exceptions.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void createUser(SaveUserDto user);

    User findUserById(Integer id) throws ResourceNotFoundException;

    void updateUser(User user);

    void deleteUser(Integer id);

    String loginUser(UserLoginDto userLoginDto);
}
