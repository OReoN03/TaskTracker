package com.example.tasktracker.service.user;

import com.example.tasktracker.rest.dto.UserLoginDto;
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

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(SaveUserDto saveUserDto) {
        userRepository.save(userRegisterDtoToUser(saveUserDto));
    }

    @Override
    public User findUserById(Integer id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Didn't find user by id: " + id));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }


    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        return "";
    }

    private User userRegisterDtoToUser(SaveUserDto saveUserDto) {
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
}
