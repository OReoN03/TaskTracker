package com.example.tasktracker.app.impl.user;

import com.example.tasktracker.app.api.user.FindAllUsersInbound;
import com.example.tasktracker.app.api.user.UserRepository;
import com.example.tasktracker.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllUsersUseCase implements FindAllUsersInbound {
    private final UserRepository userRepository;

    @Override
    public List<User> execute() {
        return userRepository.findAll();
    }
}
