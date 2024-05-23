package com.example.tasktracker.mapper;

import com.example.tasktracker.model.User;
import com.example.tasktracker.rest.dto.SaveUserDto;
import com.example.tasktracker.rest.dto.UserLoginDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User saveUserDtoToUser(SaveUserDto saveUserDto) {
        if (saveUserDto == null) return null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setLogin(saveUserDto.getLogin());
        user.setFirstName(saveUserDto.getFirstName());
        user.setPatronymic(saveUserDto.getPatronymic());
        user.setLastName(saveUserDto.getLastName());
        user.setHashPassword(encoder.encode(saveUserDto.getPassword()));
        user.setEmail(saveUserDto.getEmail());
        return user;
    }

    public User userLoginDtoToUser(UserLoginDto userLoginDto) {
        if (userLoginDto == null) return null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setLogin(userLoginDto.getLogin());
        user.setHashPassword(encoder.encode(userLoginDto.getPassword()));
        return user;
    }
}
