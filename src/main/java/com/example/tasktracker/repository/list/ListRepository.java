package com.example.tasktracker.repository.list;

import java.util.List;
import java.util.Optional;

public interface ListRepository {
    List<com.example.tasktracker.model.List> findAll();

    com.example.tasktracker.model.List save(com.example.tasktracker.model.List card);

    Optional<com.example.tasktracker.model.List> findById(Integer id);

    void deleteById(Integer id);
}
