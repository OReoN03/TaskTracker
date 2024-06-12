package com.example.tasktracker.repository.list;

import com.example.tasktracker.model.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListJpaRepository extends JpaRepository<List, Integer> {
}
