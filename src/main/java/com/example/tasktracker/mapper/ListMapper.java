package com.example.tasktracker.mapper;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Board;
import com.example.tasktracker.model.List;
import com.example.tasktracker.repository.board.BoardRepository;
import com.example.tasktracker.rest.dto.ListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListMapper {
    private final BoardRepository boardRepository;

    public List toList(ListDto listDto)  {
        List list = new List();
        list.setTitle(listDto.getTitle());

        Integer boardId = listDto.getBoardId();
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Didn't find user board id: " + boardId));
        list.setBoard(board);
        return list;
    }

    public ListDto toListDto(List list) {
        ListDto listDto = new ListDto();
        listDto.setTitle(list.getTitle());
        listDto.setBoardId(list.getBoard().getId());
        return listDto;
    }
}
