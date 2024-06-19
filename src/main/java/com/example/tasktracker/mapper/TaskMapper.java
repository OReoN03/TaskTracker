package com.example.tasktracker.mapper;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.list.ListRepository;
import com.example.tasktracker.rest.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    private final ListRepository listRepository;

    public Task toTask(TaskDto taskDto)  {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setLabel(taskDto.getLabel());

        Integer listId = taskDto.getListId();
        task.setList(listRepository.findById(listId)
                .orElseThrow((() -> new ResourceNotFoundException("Didn't find list by id: " + listId))));
        return task;
    }

    public TaskDto toTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setLabel(task.getLabel());
        taskDto.setListId(task.getList().getId());
        return taskDto;
    }
}
