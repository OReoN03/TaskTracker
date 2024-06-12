package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.TaskAlreadyClosedException;
import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.exceptions.TaskAlreadyInFirstListException;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.rest.dto.TaskDto;
import com.example.tasktracker.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name="Tasks",description = "Controller to work with tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(description = "Get task by id", method = "getTaskById")
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable @NotNull int id) throws ResourceNotFoundException {
        return taskService.findTaskById(id);
    }

    @Operation(description = "Create task by id", method = "createTask")
    @PostMapping
    public void createTask(@RequestBody TaskDto taskDto) throws ResourceNotFoundException {
        taskService.createTask(taskDto);
    }

    @Operation(description = "Update task by id", method = "updateTask")
    @PutMapping(path = "/{id}")
    public void updateTask(@PathVariable @NotNull int id, @RequestParam @NotNull Integer listId, @RequestBody Task task) throws ResourceNotFoundException {
        taskService.updateTask(id, listId, task);
    }

    @Operation(description = "Delete task by id", method = "deleteTask")
    @DeleteMapping(path = "/{id}")
    public void deleteTask(@PathVariable @NotNull int id) {
        taskService.deleteTask(id);
    }

    @Operation(description = "Take task by id", method = "takeTask")
    @PostMapping("/take/{id}")
    public Task takeTask(@PathVariable @NotNull int id, @RequestParam int userId) throws ResourceNotFoundException, TaskAlreadyClosedException {
        return taskService.takeTask(id, userId);
    }

    @Operation(description = "Close task by id", method = "closeTask")
    @PostMapping("/close/{id}")
    public Task closeTask(@PathVariable @NotNull int id) throws ResourceNotFoundException, TaskAlreadyClosedException {
        return taskService.closeTask(id);
    }

    @Operation(description = "Return task by id", method = "returnTask")
    @PostMapping("/return/{id}")
    public Task returnTask(@PathVariable @NotNull int id) throws ResourceNotFoundException, TaskAlreadyInFirstListException {
        return taskService.returnTask(id);
    }
}
