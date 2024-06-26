package com.example.tasktracker.repository.task;

import com.example.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskJpaRepository extends JpaRepository<Task, Integer> {
    List<Task> findByDeadlineBetween(LocalDateTime start, LocalDateTime end);
}
