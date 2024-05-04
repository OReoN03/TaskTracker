package com.example.tasktracker.repository.workspace;

import com.example.tasktracker.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceJpaRepository extends JpaRepository<Workspace, Integer> {
}
