package com.example.tasktracker.repository.board;

import com.example.tasktracker.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryAdapter implements BoardRepository {
    private final BoardJpaRepository boardJpaRepository;

    @Override
    public List<Board> findAll() {
        return boardJpaRepository.findAll();
    }

    @Override
    public Board save(Board board) {
        return boardJpaRepository.save(board);
    }

    @Override
    public Optional<Board> findById(Integer id) {
        return boardJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        boardJpaRepository.deleteById(id);
    }
}
