package ru.imakhnoo.toDo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.imakhnoo.toDo.entity.Task;
import ru.imakhnoo.toDo.repository.TaskRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

//    public void addNewTask(String name, int userId) {
//        Task task = new Task(name, userId);
//        this.taskRepository.save(task);
//    }

    @Async
    public Task addNewTask(String name, int userId) {
        Task task = new Task(name, userId);
        this.taskRepository.save(task);
        return task;
    }

    public void updateTaskStatus(Long taskId, boolean done) {
        Task task = this.taskRepository.findById(taskId).orElseThrow(()
        -> new NoSuchElementException("Task not found"));

        task.setDone(done);
        this.taskRepository.save(task);
    }

    public List<Task> findAllByUserId(int userId) {
        return this.taskRepository.findAllByUserId(userId);
    }

    @Async
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
        taskRepository.delete(task);
    }
}
