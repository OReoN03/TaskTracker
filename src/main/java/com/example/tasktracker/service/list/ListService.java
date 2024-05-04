package com.example.tasktracker.service.list;

import com.example.tasktracker.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ListService {
    List<com.example.tasktracker.model.List> getAllLists();

    void createList(com.example.tasktracker.model.List list);

    com.example.tasktracker.model.List findListById(Integer id) throws ResourceNotFoundException;

    void updateList(com.example.tasktracker.model.List list);

    void deleteList(Integer id);
}
