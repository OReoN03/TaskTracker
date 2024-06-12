package com.example.tasktracker.repository.board;

import com.example.tasktracker.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<Board, Integer> {
}
