package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.rest.dto.ListDto;
import com.example.tasktracker.service.list.ListService;
import com.example.tasktracker.model.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
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
    public List getListById(@PathVariable @NotNull int id) throws ResourceNotFoundException {
        return listService.findListById(id);
    }

    @PostMapping
    public void createList(@RequestBody ListDto listDto) throws ResourceNotFoundException {
        listService.createList(listDto);
    }

    @PutMapping(path = "/{id}")
    public void updateList(@PathVariable @NotNull int id, @RequestBody List list) throws ResourceNotFoundException {
       listService.updateList(id, list);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteList(@PathVariable @NotNull int id) {
        listService.deleteList(id);
    }
}
