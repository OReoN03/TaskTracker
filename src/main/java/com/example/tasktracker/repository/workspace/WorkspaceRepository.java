package com.example.tasktracker.repository.workspace;

import com.example.tasktracker.model.Workspace;

import java.util.List;
import java.util.Optional;

public interface WorkspaceRepository {
    List<Workspace> findAll();

    Workspace save(Workspace workspace);

    Optional<Workspace> findById(Integer id);

    void deleteById(Integer id);
}
