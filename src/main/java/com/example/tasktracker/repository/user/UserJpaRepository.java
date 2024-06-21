package com.example.tasktracker.repository.user;

import com.example.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByLogin(String login);
}
