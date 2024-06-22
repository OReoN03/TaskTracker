package com.example.tasktracker.service.user;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.mapper.UserMapper;
import com.example.tasktracker.model.Role;
import com.example.tasktracker.model.RoleName;
import com.example.tasktracker.model.User;
import com.example.tasktracker.repository.user.UserRepository;
import com.example.tasktracker.rest.dto.SaveUserDto;
import com.example.tasktracker.rest.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    public User register(SaveUserDto saveUserDto) {
        User user = userMapper.saveUserDtoToUser(saveUserDto);

        Role role = new Role();
        role.setName(RoleName.ROLE_USER);

        user.setRoles(Set.of(role));

        return userRepository.save(user);
    }

    public User login(UserLoginDto userLoginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getLogin(), userLoginDto.getPassword()));

        return userRepository.findByLogin(userLoginDto.getLogin()).orElseThrow(
                () -> new ResourceNotFoundException("Didn't find user by login: " + userLoginDto.getLogin()));
    }
}
