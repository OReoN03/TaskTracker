package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Workspace;
import com.example.tasktracker.rest.dto.WorkspaceDto;
import com.example.tasktracker.service.workspace.WorkspaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
@RequiredArgsConstructor
@Tag(name="Workspaces",description = "Controller to work with workspaces")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @GetMapping
    public List<Workspace> getAllWorkspaces() {
        return workspaceService.getAllWorkspaces();
    }

    @GetMapping(path = "/{id}")
    public Workspace getWorkspaceById(@PathVariable @NotNull int id) throws ResourceNotFoundException {
        return workspaceService.findWorkspaceById(id);
    }

    @PostMapping
    public void createWorkspace(@RequestBody WorkspaceDto workspaceDto) {
        workspaceService.createWorkspace(workspaceDto);
    }

    @PutMapping(path = "/{id}")
    public void updateWorkspace(@PathVariable @NotNull int id, @RequestBody Workspace workspace) throws ResourceNotFoundException {
        workspaceService.updateWorkspace(id, workspace);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteWorkspace(@PathVariable @NotNull int id) {
        workspaceService.deleteWorkspace(id);
    }
}
