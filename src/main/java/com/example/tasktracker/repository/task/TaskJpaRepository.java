package com.example.tasktracker.repository.task;

import com.example.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<Task, Integer> {
}
