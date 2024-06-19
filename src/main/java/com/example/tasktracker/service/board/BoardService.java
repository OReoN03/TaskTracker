package com.example.tasktracker.service.board;

import com.example.tasktracker.model.Board;
import com.example.tasktracker.rest.dto.BoardDto;

import java.util.List;

public interface BoardService {
    List<Board> getAllBoards();

    Board createBoard(BoardDto boardDto);

    Board findBoardById(Integer id);

    void updateBoard(int id, Board board);

    void deleteBoard(Integer id);
}
