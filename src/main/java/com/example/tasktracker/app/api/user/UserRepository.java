package com.example.tasktracker.app.api.user;

import com.example.tasktracker.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    User save(User user);

    Optional<User> findById(Long id);
}
