package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Board;
import com.example.tasktracker.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public List<Board> getBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping(path = "/{id}")
    public Board getBoard(@PathVariable int id) throws ResourceNotFoundException {
        return boardService.findBoardById(id);
    }

    @PostMapping
    public void createBoard(@RequestBody Board board) {
        boardService.createBoard(board);
    }

    @PutMapping
    public void updateBoard(@RequestBody Board board) {
        boardService.updateBoard(board);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
    }
}
