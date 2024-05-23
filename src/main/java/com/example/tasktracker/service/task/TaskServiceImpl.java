package com.example.tasktracker.service.task;

import com.example.tasktracker.exceptions.TaskAlreadyClosedException;
import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Board;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.list.ListRepository;
import com.example.tasktracker.repository.task.TaskRepository;
import com.example.tasktracker.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ListRepository listRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void createTask(Task Task) {
        taskRepository.save(Task);
    }

    @Override
    public Task findTaskById(Integer id) throws ResourceNotFoundException {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Didn't find task by id: " + id));
    }

    @Override
    public void updateTask(int id, Integer listId, Task task) throws ResourceNotFoundException {
        com.example.tasktracker.model.List list = listRepository.findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException("Didn't find list by id: " + id));

        Task taskToUpdate = findTaskById(id);
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setDeadline(task.getDeadline());
        taskToUpdate.setAssignee(task.getAssignee());
        taskToUpdate.setList(list);

        taskRepository.save(taskToUpdate);
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task takeTask(Integer taskId, Integer userId) throws ResourceNotFoundException, TaskAlreadyClosedException {
        Task task = findTaskById(taskId);
        com.example.tasktracker.model.List list = task.getList();
        Board board = list.getBoard();

        int listIndex = board.getLists().indexOf(list);
        if (listIndex == board.getLists().size() - 1) throw new TaskAlreadyClosedException("Can't take closed task.");

        task.setAssignee(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Didn't find user by id: " + userId)));
        moveTaskToNextList(task);

        return taskRepository.save(task);
    }

    @Override
    public Task returnTask(int taskId) throws ResourceNotFoundException {
        Task task = findTaskById(taskId);
        task.setAssignee(null);

        com.example.tasktracker.model.List list = task.getList();
        Board board = list.getBoard();

        int listIndex = board.getLists().indexOf(list);
        if (listIndex != 0) {
            moveTaskToPreviousList(task);
        }
        return taskRepository.save(task);
    }

    @Override
    public Task closeTask(Integer taskId) throws ResourceNotFoundException {
        Task task = findTaskById(taskId);
        task.setAssignee(null);

        moveTaskToNextList(task);
        return taskRepository.save(task);
    }

    public void moveTaskToNextList(Task task) {
        com.example.tasktracker.model.List list = task.getList();
        Board board = list.getBoard();

        int listIndex = board.getLists().indexOf(list);
        com.example.tasktracker.model.List nextList = board.getLists().get(listIndex + 1);

        list.removeTask(task);
        nextList.addTask(task);

        task.setList(nextList);
    }

    public void moveTaskToPreviousList(Task task) {
        com.example.tasktracker.model.List list = task.getList();
        Board board = list.getBoard();

        int listIndex = board.getLists().indexOf(list);
        if (listIndex != 0) {
            com.example.tasktracker.model.List previousList = board.getLists().get(listIndex - 1);

            list.removeTask(task);
            previousList.addTask(task);

            task.setList(previousList);
        }
    }
}
