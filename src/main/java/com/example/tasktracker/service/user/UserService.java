package com.example.tasktracker.service.user;

import com.example.tasktracker.model.User;
import com.example.tasktracker.rest.dto.UserLoginDto;
import com.example.tasktracker.rest.dto.SaveUserDto;
import com.example.tasktracker.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void createUser(SaveUserDto user);

    User findUserById(Integer id) throws ResourceNotFoundException;

    void updateUser(int id, SaveUserDto saveUserDto) throws ResourceNotFoundException;

    void deleteUser(Integer id);
}
