package com.example.tasktracker.service.task;

import com.example.tasktracker.model.Task;
import com.example.tasktracker.rest.dto.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    List<Task> getAllTasks();

    Task createTask(TaskDto taskDto);

    Task findTaskById(Integer id) ;

    void updateTask(int id, Integer listId, Task task);

    void deleteTask(Integer id);

    Task takeTask(Integer taskId, Integer userId);

    Task closeTask(Integer taskId);

    void moveTaskToNextList(Task task);

    Task returnTask(int taskId);
}
