package com.example.tasktracker.service.board;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Board;

import java.util.List;

public interface BoardService {
    List<Board> getAllBoards();

    void createBoard(Board board);

    Board findBoardById(Integer id) throws ResourceNotFoundException;

    void updateBoard(Board board);

    void deleteBoard(Integer id);
}
