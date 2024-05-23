package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Workspace;
import com.example.tasktracker.service.workspace.WorkspaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public Workspace getWorkspaceById(@PathVariable int id) throws ResourceNotFoundException {
        return workspaceService.findWorkspaceById(id);
    }

    @PostMapping
    public void createWorkspace(@RequestBody Workspace workspace) {
        workspaceService.createWorkspace(workspace);
    }

    @PutMapping(path = "/{id}")
    public void updateWorkspace(@PathVariable int id, @RequestBody Workspace workspace) throws ResourceNotFoundException {
        Workspace workspaceToUpdate = workspaceService.findWorkspaceById(id);

        workspaceToUpdate.setTitle(workspace.getTitle());
        workspaceToUpdate.setDescription(workspace.getDescription());
        workspaceToUpdate.setAdmins(workspace.getAdmins());
        workspaceToUpdate.setBoards(workspace.getBoards());
        workspaceToUpdate.setGuests(workspace.getGuests());

        workspaceService.updateWorkspace(workspaceToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteWorkspace(@PathVariable int id) {
        workspaceService.deleteWorkspace(id);
    }
}
