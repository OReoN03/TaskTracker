package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Board;
import com.example.tasktracker.rest.dto.BoardDto;
import com.example.tasktracker.service.board.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Tag(name="Boards",description = "Controller to work with boards")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping(path = "/{id}")
    public Board getBoard(@PathVariable @NotNull int id) throws ResourceNotFoundException {
        return boardService.findBoardById(id);
    }

    @PostMapping
    public void createBoard(@RequestBody BoardDto boardDto) throws ResourceNotFoundException {
        boardService.createBoard(boardDto);
    }

    @PutMapping(path = "/{id}")
    public void updateBoard(@PathVariable @NotNull int id, @RequestBody Board board) throws ResourceNotFoundException {
        boardService.updateBoard(id, board);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBoard(@PathVariable @NotNull int id) {
        boardService.deleteBoard(id);
    }
}
