package com.example.tasktracker.repository.user;

import com.example.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
}
