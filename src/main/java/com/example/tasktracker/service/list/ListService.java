package com.example.tasktracker.service.list;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.rest.dto.ListDto;

import java.util.List;

public interface ListService {
    List<com.example.tasktracker.model.List> getAllLists();

    void createList(ListDto listDto) throws ResourceNotFoundException;

    com.example.tasktracker.model.List findListById(Integer id) throws ResourceNotFoundException;

    void updateList(int id, com.example.tasktracker.model.List list) throws ResourceNotFoundException;

    void deleteList(Integer id);
}
