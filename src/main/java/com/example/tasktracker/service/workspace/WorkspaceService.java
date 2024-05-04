package com.example.tasktracker.service.workspace;


import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Workspace;

import java.util.List;

public interface WorkspaceService {
    List<Workspace> getAllWorkspaces();

    void createWorkspace(Workspace workspace);

    Workspace findWorkspaceById(Integer id) throws ResourceNotFoundException;

    void updateWorkspace(Workspace workspace);

    void deleteWorkspace(Integer id);
}
