package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Card;
import com.example.tasktracker.model.User;
import com.example.tasktracker.rest.dto.UserRegisterDto;
import com.example.tasktracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable int id) throws ResourceNotFoundException {
        return userService.findUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody UserRegisterDto userRegisterDto) {
        userService.createUser(userRegisterDto);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
