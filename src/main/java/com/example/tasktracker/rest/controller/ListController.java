package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.service.list.ListService;
import com.example.tasktracker.model.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
@Tag(name="Lists",description = "Controller to work with lists")
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

    @PutMapping(path = "/{id}")
    public void updateList(@PathVariable int id, @RequestBody List list) throws ResourceNotFoundException {
        List listToUpdate = listService.findListById(id);

        listToUpdate.setTitle(list.getTitle());
        listToUpdate.setBoard(list.getBoard());
        listToUpdate.setTasks(list.getTasks());

        listService.updateList(listToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteList(@PathVariable int id) {
        listService.deleteList(id);
    }
}
