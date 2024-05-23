package com.example.tasktracker.mapper;

import com.example.tasktracker.model.Workspace;
import com.example.tasktracker.rest.dto.WorkspaceDto;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceMapper {
    public WorkspaceDto toWorkspaceDto(Workspace workspace) {
        WorkspaceDto workspaceDto = new WorkspaceDto();
        workspaceDto.setId(workspace.getId());
        workspaceDto.setTitle(workspace.getTitle());
        workspaceDto.setDescription(workspace.getDescription());
        return workspaceDto;
    }

    public Workspace toWorkspace(WorkspaceDto workspaceDto) {
        Workspace workspace = new Workspace();
        workspace.setTitle(workspaceDto.getTitle());
        workspace.setDescription(workspaceDto.getDescription());
        return workspace;
    }
}
