package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Board;
import com.example.tasktracker.service.board.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public Board getBoard(@PathVariable int id) throws ResourceNotFoundException {
        return boardService.findBoardById(id);
    }

    @PostMapping
    public void createBoard(@RequestBody Board board) {
        boardService.createBoard(board);
    }

    @PutMapping(path = "/{id}")
    public void updateBoard(@PathVariable int id, @RequestBody Board board) throws ResourceNotFoundException {
        Board boardToUpdate = boardService.findBoardById(id);

        boardToUpdate.setTitle(board.getTitle());
        boardToUpdate.setDescription(board.getDescription());
        boardToUpdate.setLists(board.getLists());
        boardToUpdate.setAdmins(board.getAdmins());
        boardToUpdate.setGuests(board.getGuests());

        boardService.updateBoard(board);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
    }
}
