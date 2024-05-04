package com.example.tasktracker.service.workspace;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Workspace;
import com.example.tasktracker.repository.workspace.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;

    @Override
    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    @Override
    public void createWorkspace(Workspace workspace) {
        workspaceRepository.save(workspace);
    }

    @Override
    public Workspace findWorkspaceById(Integer id) throws ResourceNotFoundException {
        return workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Didn't find workspace by id: " + id));
    }

    @Override
    public void updateWorkspace(Workspace workspace) {
        workspaceRepository.save(workspace);
    }

    @Override
    public void deleteWorkspace(Integer id) {
        workspaceRepository.deleteById(id);
    }
}
