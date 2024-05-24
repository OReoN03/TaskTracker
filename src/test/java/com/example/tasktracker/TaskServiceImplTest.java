package com.example.tasktracker;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.exceptions.TaskAlreadyClosedException;
import com.example.tasktracker.mapper.TaskMapper;
import com.example.tasktracker.model.Board;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.model.User;
import com.example.tasktracker.repository.list.ListRepository;
import com.example.tasktracker.repository.task.TaskRepository;
import com.example.tasktracker.repository.user.UserRepository;
import com.example.tasktracker.rest.dto.TaskDto;
import com.example.tasktracker.service.task.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {
    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ListRepository listRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskMapper taskMapper;

    @Test
    public void testGetAllTasks() {
        Task task1 = new Task();
        Task task2 = new Task();

        task1.setId(1);
        task1.setTitle("Task 1");
        task1.setDescription("Description 1");
        task1.setLabel("Label 1");

        task2.setId(2);
        task2.setTitle("Task 2");
        task2.setDescription("Description 2");
        task2.setLabel("Label 2");

        List<Task> mockTasks = List.of(task1, task2);

        when(taskRepository.findAll()).thenReturn(mockTasks);

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
        assertEquals(task1.getTitle(), tasks.get(0).getTitle());
        assertEquals(task2.getTitle(), tasks.get(1).getTitle());

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testCreateTask() throws ResourceNotFoundException {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("New Title");
        taskDto.setDescription("New Description");

        Task task = new Task();
        task.setId(3);
        task.setTitle("New Title");
        task.setDescription("New Description");

        when(taskMapper.toTask(taskDto)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);

        assertDoesNotThrow(() -> taskService.createTask(taskDto));

        verify(taskMapper, times(1)).toTask(taskDto);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testFindTaskById() throws ResourceNotFoundException {
        Task task = new Task();
        task.setId(1);
        task.setTitle("Task 1");
        task.setDescription("Description 1");

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        Task foundTask = taskService.findTaskById(1);

        assertEquals(task.getTitle(), foundTask.getTitle());
        assertEquals(task.getDescription(), foundTask.getDescription());

        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateTask() {
        com.example.tasktracker.model.List list = new com.example.tasktracker.model.List();
        list.setId(1);
        list.setTitle("List");

        Task existingTask = new Task();
        existingTask.setId(1);
        existingTask.setTitle("Task");
        existingTask.setDescription("Description");
        existingTask.setList(list);

        Task updatedTask = new Task();
        updatedTask.setId(1);
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");
        updatedTask.setList(list);

        when(listRepository.findById(1)).thenReturn(Optional.of(list));
        when(taskRepository.findById(1)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(updatedTask)).thenReturn(updatedTask);

        assertDoesNotThrow(() -> taskService.updateTask(1, list.getId(), updatedTask));

        verify(taskRepository, times(1)).findById(1);
        verify(taskRepository, times(1)).save(updatedTask);
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task();
        task.setId(1);
        task.setTitle("Task");
        task.setDescription("Description");

        assertDoesNotThrow(() -> taskService.deleteTask(1));

        verify(taskRepository, times(1)).deleteById(task.getId());
    }

    @Test
    public void testTakeTask() {
        Task task = new Task();
        task.setId(1);
        task.setTitle("Task");
        task.setDescription("Description");

        com.example.tasktracker.model.List list1 = new com.example.tasktracker.model.List();
        list1.setId(1);
        list1.setTitle("List 1");

        com.example.tasktracker.model.List list2 = new com.example.tasktracker.model.List();
        list2.setId(2);
        list2.setTitle("List 2");

        Board board = new Board();
        board.setLists(List.of(list1, list2));
        list1.setBoard(board);
        list2.setBoard(board);
        task.setList(list1);

        User user = new User();
        user.setId(1);
        user.setLogin("User");

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> taskService.takeTask(1, 1));
        assertEquals(task.getAssignee().getId(), user.getId());
        assertEquals(task.getList().getId(), list2.getId());

        verify(taskRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findById(1);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testReturnTask() {
        Task task = new Task();
        task.setId(1);
        task.setTitle("Task");
        task.setDescription("Description");

        com.example.tasktracker.model.List list1 = new com.example.tasktracker.model.List();
        list1.setId(1);
        list1.setTitle("List 1");

        com.example.tasktracker.model.List list2 = new com.example.tasktracker.model.List();
        list1.setId(2);
        list1.setTitle("List 2");

        Board board = new Board();
        board.setLists(List.of(list1, list2));
        list1.setBoard(board);
        list2.setBoard(board);
        task.setList(list2);

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        assertDoesNotThrow(() -> taskService.returnTask(1));
        assertEquals(task.getList().getId(), list1.getId());

        verify(taskRepository, times(1)).findById(1);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testCloseTaskFailed() {
        Task task = new Task();
        task.setId(1);
        task.setTitle("Task");
        task.setDescription("Description");

        com.example.tasktracker.model.List list = new com.example.tasktracker.model.List();
        Board board = new Board();

        board.setLists(List.of(list));
        list.setBoard(board);
        task.setList(list);

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        //assertDoesNotThrow(() -> taskService.closeTask(1));
        assertThrows(TaskAlreadyClosedException.class, () -> taskService.closeTask(1));

        verify(taskRepository, times(1)).findById(1);
        //verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testCloseTaskSuccessful() {
        Task task = new Task();
        task.setId(1);
        task.setTitle("Task");
        task.setDescription("Description");

        com.example.tasktracker.model.List list1 = new com.example.tasktracker.model.List();
        list1.setId(1);
        list1.setTitle("List 1");

        com.example.tasktracker.model.List list2 = new com.example.tasktracker.model.List();
        list2.setId(2);
        list2.setTitle("List 2");

        Board board = new Board();
        board.setLists(List.of(list1, list2));
        list1.setBoard(board);
        list2.setBoard(board);
        task.setList(list1);

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        assertDoesNotThrow(() -> taskService.closeTask(1));

        verify(taskRepository, times(1)).findById(1);
        verify(taskRepository, times(1)).save(task);
    }
}
