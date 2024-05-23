package com.example.tasktracker.repository.task;

import com.example.tasktracker.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();

    Task save(Task task);

    Optional<Task> findById(Integer id);

    void deleteById(Integer id);
}
