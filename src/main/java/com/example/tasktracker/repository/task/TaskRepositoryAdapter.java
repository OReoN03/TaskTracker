package com.example.tasktracker.repository.task;

import com.example.tasktracker.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryAdapter implements TaskRepository {
    private final TaskJpaRepository taskJpaRepository;

    @Override
    public List<Task> findAll() {
        return taskJpaRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        return taskJpaRepository.save(task);
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return taskJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        taskJpaRepository.deleteById(id);
    }
}
