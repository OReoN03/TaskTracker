package com.example.tasktracker.service.user;

import com.example.tasktracker.rest.dto.UserLoginDto;
import com.example.tasktracker.rest.dto.UserRegisterDto;
import com.example.tasktracker.repository.user.UserRepository;
import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.User;
import lombok.RequiredArgsConstructor;
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
    public void createUser(UserRegisterDto userRegisterDto) {
        userRepository.save(userRegisterDtoToUser(userRegisterDto));
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

    private User userRegisterDtoToUser(UserRegisterDto userRegisterDto) {
        if (userRegisterDto == null) return null;
        User user = new User();
        user.setLogin(userRegisterDto.getLogin());
        user.setFirstName(userRegisterDto.getFirstName());
        user.setPatronymic(userRegisterDto.getPatronymic());
        user.setLastName(userRegisterDto.getLastName());
        user.setHashPassword(userRegisterDto.getHashPassword());
        user.setEmail(userRegisterDto.getEmail());
        return user;
    }
}
