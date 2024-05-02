package com.example.tasktracker.adapter.persistence.user;

import com.example.tasktracker.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
