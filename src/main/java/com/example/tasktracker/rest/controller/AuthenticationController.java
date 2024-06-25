package com.example.tasktracker.rest.controller;

import com.example.tasktracker.mapper.UserMapper;
import com.example.tasktracker.model.User;
import com.example.tasktracker.rest.dto.LoginResponseDto;
import com.example.tasktracker.rest.dto.SaveUserDto;
import com.example.tasktracker.rest.dto.UserDto;
import com.example.tasktracker.rest.dto.UserLoginDto;
import com.example.tasktracker.service.security.AuthenticationService;
import com.example.tasktracker.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public UserDto register(@RequestBody SaveUserDto saveUserDto) {
        return userMapper.userToUserDto(authenticationService.register(saveUserDto));
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody UserLoginDto userLoginDto) {
        User user = authenticationService.login(userLoginDto);

        String token = jwtService.generateToken(user);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);
        loginResponseDto.setExpiresIn(jwtService.getExpirationTime());
        return loginResponseDto;
    }
}
