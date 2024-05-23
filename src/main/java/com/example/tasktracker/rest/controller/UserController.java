package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.User;
import com.example.tasktracker.rest.dto.SaveUserDto;
import com.example.tasktracker.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name="Users",description = "Controller to work with users")
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
    public void createUser(@RequestBody SaveUserDto saveUserDto) {
        userService.createUser(saveUserDto);
    }

    @PutMapping(path = "/{id}")
    public void updateUser(@PathVariable int id, @RequestBody SaveUserDto saveUserDto) throws ResourceNotFoundException {
        User userToUpdate = userService.findUserById(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        userToUpdate.setFirstName(saveUserDto.getFirstName());
        userToUpdate.setPatronymic(saveUserDto.getPatronymic());
        userToUpdate.setLastName(saveUserDto.getLastName());
        userToUpdate.setHashPassword(encoder.encode(saveUserDto.getPassword()));
        userToUpdate.setEmail(saveUserDto.getEmail());

        userService.updateUser(userToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
