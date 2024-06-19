package com.example.tasktracker.service.workspace;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.mapper.WorkspaceMapper;
import com.example.tasktracker.model.Workspace;
import com.example.tasktracker.repository.workspace.WorkspaceRepository;
import com.example.tasktracker.rest.dto.WorkspaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;

    @Override
    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    @Override
    public void createWorkspace(WorkspaceDto workspaceDto) {
        workspaceRepository.save(workspaceMapper.toWorkspace(workspaceDto));
    }

    @Override
    public Workspace findWorkspaceById(Integer id) {
        return workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Didn't find workspace by id: " + id));
    }

    @Override
    public void updateWorkspace(int id, Workspace workspace) {
        Workspace workspaceToUpdate = findWorkspaceById(id);

        workspaceToUpdate.setTitle(workspace.getTitle());
        workspaceToUpdate.setDescription(workspace.getDescription());
        workspaceToUpdate.setAdmins(workspace.getAdmins());
        workspaceToUpdate.setBoards(workspace.getBoards());
        workspaceToUpdate.setGuests(workspace.getGuests());

        workspaceRepository.save(workspaceToUpdate);
    }

    @Override
    public void deleteWorkspace(Integer id) {
        workspaceRepository.deleteById(id);
    }
}
