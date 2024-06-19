package com.example.tasktracker.service.board;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.mapper.BoardMapper;
import com.example.tasktracker.model.Board;
import com.example.tasktracker.repository.board.BoardRepository;
import com.example.tasktracker.rest.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board createBoard(BoardDto boardDto) {
        return boardRepository.save(boardMapper.toBoard(boardDto));
    }

    @Override
    public Board findBoardById(Integer id) {
        return boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Didn't find board by id: " + id));
    }

    @Override
    public void updateBoard(int id, Board board) {
        Board boardToUpdate = findBoardById(id);

        boardToUpdate.setTitle(board.getTitle());
        boardToUpdate.setDescription(board.getDescription());
        boardToUpdate.setLists(board.getLists());
        boardToUpdate.setAdmins(board.getAdmins());
        boardToUpdate.setGuests(board.getGuests());

        boardRepository.save(boardToUpdate);
    }

    @Override
    public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }
}
