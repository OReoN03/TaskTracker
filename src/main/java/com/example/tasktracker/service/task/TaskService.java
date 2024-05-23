package com.example.tasktracker.service.task;

import com.example.tasktracker.exceptions.TaskAlreadyClosedException;
import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    List<Task> getAllTasks();

    void createTask(Task Task);

    Task findTaskById(Integer id) throws ResourceNotFoundException;

    void updateTask(int id, Integer listId, Task task) throws ResourceNotFoundException;

    void deleteTask(Integer id);

    Task takeTask(Integer taskId, Integer userId) throws ResourceNotFoundException, TaskAlreadyClosedException;

    Task closeTask(Integer taskId) throws ResourceNotFoundException;

    void moveTaskToNextList(Task task);

    Task returnTask(int taskId) throws ResourceNotFoundException;
}
