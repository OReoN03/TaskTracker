package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.service.list.ListService;
import com.example.tasktracker.model.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class ListController {
    private final ListService listService;

    @GetMapping
    public java.util.List<List> getAllLists() {
        return listService.getAllLists();
    }

    @GetMapping(path = "/{id}")
    public List getListById(@PathVariable int id) throws ResourceNotFoundException {
        return listService.findListById(id);
    }

    @PostMapping
    public void createList(@RequestBody List list) {
        listService.createList(list);
    }

    @PutMapping
    public void updateList(@RequestBody List list) {
        listService.updateList(list);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteList(@PathVariable int id) {
        listService.deleteList(id);
    }
}
