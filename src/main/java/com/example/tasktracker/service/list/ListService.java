package com.example.tasktracker.service.list;

import com.example.tasktracker.rest.dto.ListDto;

import java.util.List;

public interface ListService {
    List<com.example.tasktracker.model.List> getAllLists();

    com.example.tasktracker.model.List createList(ListDto listDto);

    com.example.tasktracker.model.List findListById(Integer id);

    void updateList(int id, com.example.tasktracker.model.List list);

    void deleteList(Integer id);
}
