package com.example.tasktracker.repository.user;

import com.example.tasktracker.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    User save(User user);

    Optional<User> findById(Integer id);

    void deleteById(Integer id);
}
