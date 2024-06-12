package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.User;
import com.example.tasktracker.rest.dto.SaveUserDto;
import com.example.tasktracker.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name="Users",description = "Controller to work with users")
public class UserController {
    private final UserService userService;

    @Operation(description = "Get all users", method = "getAllUsers")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(description = "Get user by id", method = "getUserById")
    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable @NotNull int id) throws ResourceNotFoundException {
        return userService.findUserById(id);
    }

    @Operation(description = "Create user", method = "createUser")
    @PostMapping
    public void createUser(@RequestBody SaveUserDto saveUserDto) {
        userService.createUser(saveUserDto);
    }

    @Operation(description = "Update user by id", method = "updateUser")
    @PutMapping(path = "/{id}")
    public void updateUser(@PathVariable @NotNull int id, @RequestBody SaveUserDto saveUserDto) throws ResourceNotFoundException {
        userService.updateUser(id, saveUserDto);
    }

    @Operation(description = "Delete user by id", method = "deleteUser")
    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable @NotNull int id) {
        userService.deleteUser(id);
    }
}
