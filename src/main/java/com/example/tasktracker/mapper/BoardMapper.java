package com.example.tasktracker.mapper;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Board;
import com.example.tasktracker.model.Workspace;
import com.example.tasktracker.repository.workspace.WorkspaceRepository;
import com.example.tasktracker.rest.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardMapper {
    private final WorkspaceRepository workspaceRepository;

    public Board toBoard(BoardDto boardDto) throws ResourceNotFoundException {
        Board board = new Board();
        board.setId(boardDto.getId());
        board.setTitle(boardDto.getTitle());
        board.setDescription(boardDto.getDescription());

        Integer workspaceId = boardDto.getWorkspaceId();
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Didn't find workspace by id: " + workspaceId));
        board.setWorkspace(workspace);
        return board;
    }

    public BoardDto toBoardDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setDescription(board.getDescription());
        boardDto.setWorkspaceId(board.getWorkspace().getId());
        return boardDto;
    }
}
