package com.example.tasktracker.repository.role;

import com.example.tasktracker.model.Role;
import com.example.tasktracker.model.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {
    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleJpaRepository.findByName(name);
    }
}
