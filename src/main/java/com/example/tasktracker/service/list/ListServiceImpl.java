package com.example.tasktracker.service.list;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.mapper.ListMapper;
import com.example.tasktracker.model.List;
import com.example.tasktracker.repository.list.ListRepository;
import com.example.tasktracker.rest.dto.ListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {
    private final ListRepository listRepository;
    private final ListMapper listMapper;

    @Override
    public java.util.List<List> getAllLists() {
        return listRepository.findAll();
    }

    @Override
    public List createList(ListDto listDto) {
        return listRepository.save(listMapper.toList(listDto));
    }

    @Override
    public List findListById(Integer id) {
        return listRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Didn't find list by id: " + id));
    }

    @Override
    public void updateList(int id, List list) {
        List listToUpdate = findListById(id);

        listToUpdate.setTitle(list.getTitle());
        listToUpdate.setBoard(list.getBoard());
        listToUpdate.setTasks(list.getTasks());

        listRepository.save(listToUpdate);
    }

    @Override
    public void deleteList(Integer id) {
        listRepository.deleteById(id);
    }
}
