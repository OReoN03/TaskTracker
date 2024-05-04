package com.example.tasktracker.service.board;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Board;
import com.example.tasktracker.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public void createBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public Board findBoardById(Integer id) throws ResourceNotFoundException {
        return boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Didn't find board by id: " + id));
    }

    @Override
    public void updateBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }
}
