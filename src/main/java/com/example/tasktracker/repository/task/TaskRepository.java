package com.example.tasktracker.repository.task;

import com.example.tasktracker.model.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();

    List<Task> findByDeadlineBetween(LocalDateTime start, LocalDateTime end);

    Task save(Task task);

    Optional<Task> findById(Integer id);

    void deleteById(Integer id);
}
