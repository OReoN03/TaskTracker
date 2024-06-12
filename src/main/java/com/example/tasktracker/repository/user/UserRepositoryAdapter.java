package com.example.tasktracker.repository.user;

import com.example.tasktracker.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        userJpaRepository.deleteById(id);
    }
}