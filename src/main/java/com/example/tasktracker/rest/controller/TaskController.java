package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.TaskAlreadyClosedException;
import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.service.task.TaskService;
import com.example.tasktracker.service.list.ListService;
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
    private final ListService listService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable @NotNull int id) throws ResourceNotFoundException {
        return taskService.findTaskById(id);
    }

    @PostMapping
    public void createTask(@RequestParam Integer listId, @RequestBody Task task) throws ResourceNotFoundException {
        com.example.tasktracker.model.List list = listService.findListById(listId);
        task.setList(list);
        taskService.createTask(task);
    }

    @PutMapping(path = "/{id}")
    public void updateTask(@PathVariable @NotNull int id, @RequestParam @NotNull Integer listId, @RequestBody Task task) throws ResourceNotFoundException {
        taskService.updateTask(id, listId, task);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTask(@PathVariable @NotNull int id) {
        taskService.deleteTask(id);
    }

    @PostMapping("/{taskId}/take")
    public Task takeTask(@PathVariable @NotNull int taskId, @RequestParam int userId) throws ResourceNotFoundException, TaskAlreadyClosedException {
        return taskService.takeTask(taskId, userId);
    }

    @PostMapping("/{taskId}/close")
    public Task closeTask(@PathVariable @NotNull int taskId) throws ResourceNotFoundException {
        return taskService.closeTask(taskId);
    }

    @PostMapping("/{taskId}/return")
    public Task returnTask(@PathVariable @NotNull int taskId) throws ResourceNotFoundException {
        return taskService.returnTask(taskId);
    }
}
