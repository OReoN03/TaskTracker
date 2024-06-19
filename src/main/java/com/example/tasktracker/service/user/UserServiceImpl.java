package com.example.tasktracker.service.user;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.mapper.UserMapper;
import com.example.tasktracker.model.User;
import com.example.tasktracker.repository.user.UserRepository;
import com.example.tasktracker.rest.dto.SaveUserDto;
import com.example.tasktracker.rest.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::userToUserDto).toList();
    }

    @Override
    public UserDto createUser(SaveUserDto saveUserDto) {
        return userMapper.userToUserDto(userRepository.save(userMapper.saveUserDtoToUser(saveUserDto)));
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Didn't find user by id: " + id));
        return userMapper.userToUserDto(user);
    }

    @Override
    public void updateUser(int id, SaveUserDto saveUserDto) {
        userRepository.save(userMapper.saveUserDtoToUser(saveUserDto));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
