package com.example.tasktracker.repository.board;

import com.example.tasktracker.model.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    List<Board> findAll();

    Board save(Board board);

    Optional<Board> findById(Integer id);

    void deleteById(Integer id);
}
