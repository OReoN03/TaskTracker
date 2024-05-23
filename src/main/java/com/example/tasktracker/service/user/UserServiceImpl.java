package com.example.tasktracker.service.user;

import com.example.tasktracker.mapper.UserMapper;
import com.example.tasktracker.rest.dto.SaveUserDto;
import com.example.tasktracker.repository.user.UserRepository;
import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(SaveUserDto saveUserDto) {
        userRepository.save(userMapper.saveUserDtoToUser(saveUserDto));
    }

    @Override
    public User findUserById(Integer id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Didn't find user by id: " + id));
    }

    @Override
    public void updateUser(int id, SaveUserDto saveUserDto) throws ResourceNotFoundException {
        User userToUpdate = findUserById(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        userToUpdate.setFirstName(saveUserDto.getFirstName());
        userToUpdate.setPatronymic(saveUserDto.getPatronymic());
        userToUpdate.setLastName(saveUserDto.getLastName());
        userToUpdate.setHashPassword(encoder.encode(saveUserDto.getPassword()));
        userToUpdate.setEmail(saveUserDto.getEmail());

        userRepository.save(userToUpdate);
    }


    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
