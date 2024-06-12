package com.example.tasktracker.repository.role;

import com.example.tasktracker.model.Role;
import com.example.tasktracker.model.RoleName;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(RoleName name);
}
