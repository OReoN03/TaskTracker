package com.example.tasktracker.service.user;

import com.example.tasktracker.model.User;
import com.example.tasktracker.rest.dto.UserLoginDto;
import com.example.tasktracker.rest.dto.UserRegisterDto;
import com.example.tasktracker.exceptions.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void createUser(UserRegisterDto user);

    User findUserById(Integer id) throws ResourceNotFoundException;

    void updateUser(User user);

    void deleteUser(Integer id);

    String loginUser(UserLoginDto userLoginDto);
}
