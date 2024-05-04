package com.example.tasktracker.service.list;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.List;
import com.example.tasktracker.repository.list.ListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {
    private final ListRepository listRepository;


    @Override
    public java.util.List<List> getAllLists() {
        return listRepository.findAll();
    }

    @Override
    public void createList(List list) {
        listRepository.save(list);
    }

    @Override
    public List findListById(Integer id) throws ResourceNotFoundException {
        return listRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Didn't find list by id: " + id));
    }

    @Override
    public void updateList(List list) {
        listRepository.save(list);
    }

    @Override
    public void deleteList(Integer id) {
        listRepository.deleteById(id);
    }
}
