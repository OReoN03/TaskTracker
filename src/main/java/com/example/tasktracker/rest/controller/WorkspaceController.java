package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Workspace;
import com.example.tasktracker.service.workspace.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @GetMapping
    public List<Workspace> getAllWorkspaces() {
        return workspaceService.getAllWorkspaces();
    }

    @GetMapping(path = "/{id}")
    public Workspace getWorkspaceById(@PathVariable int id) throws ResourceNotFoundException {
        return workspaceService.findWorkspaceById(id);
    }

    @PostMapping
    public void createWorkspace(@RequestBody Workspace workspace) {
        workspaceService.createWorkspace(workspace);
    }

    @PutMapping
    public void updateWorkspace(@RequestBody Workspace workspace) {
        workspaceService.updateWorkspace(workspace);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteWorkspace(@PathVariable int id) {
        workspaceService.deleteWorkspace(id);
    }
}
