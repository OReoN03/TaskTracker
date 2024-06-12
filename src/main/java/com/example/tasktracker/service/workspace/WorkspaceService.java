package com.example.tasktracker.service.workspace;


import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Workspace;
import com.example.tasktracker.rest.dto.WorkspaceDto;

import java.util.List;

public interface WorkspaceService {
    List<Workspace> getAllWorkspaces();

    void createWorkspace(WorkspaceDto workspaceDto);

    Workspace findWorkspaceById(Integer id) throws ResourceNotFoundException;

    void updateWorkspace(int id, Workspace workspace) throws ResourceNotFoundException;

    void deleteWorkspace(Integer id);
}
