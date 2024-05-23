package com.example.tasktracker.service.board;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Board;
import com.example.tasktracker.rest.dto.BoardDto;

import java.util.List;

public interface BoardService {
    List<Board> getAllBoards();

    void createBoard(BoardDto boardDto) throws ResourceNotFoundException;

    Board findBoardById(Integer id) throws ResourceNotFoundException;

    void updateBoard(int id, Board board) throws ResourceNotFoundException;

    void deleteBoard(Integer id);
}
