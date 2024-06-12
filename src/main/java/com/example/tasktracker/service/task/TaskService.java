package com.example.tasktracker.service.task;

import com.example.tasktracker.exceptions.TaskAlreadyClosedException;
import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.exceptions.TaskAlreadyInFirstListException;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.rest.dto.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    List<Task> getAllTasks();

    void createTask(TaskDto taskDto) throws ResourceNotFoundException;

    Task findTaskById(Integer id) throws ResourceNotFoundException;

    void updateTask(int id, Integer listId, Task task) throws ResourceNotFoundException;

    void deleteTask(Integer id);

    Task takeTask(Integer taskId, Integer userId) throws ResourceNotFoundException, TaskAlreadyClosedException;

    Task closeTask(Integer taskId) throws ResourceNotFoundException, TaskAlreadyClosedException;

    void moveTaskToNextList(Task task) throws TaskAlreadyClosedException;

    Task returnTask(int taskId) throws ResourceNotFoundException, TaskAlreadyInFirstListException;
}
