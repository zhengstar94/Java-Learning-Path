package com.example.todoapp.task;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public Task create(String title) {
        return repo.save(new Task(title));
    }

    public List<Task> list() {
        return repo.findAll();
    }

    @Transactional
    public Task update(Long id, String title, Boolean completed) {
        Task task = repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found: " + id));
        if (title != null) task.setTitle(title);
        if (completed != null) task.setCompleted(completed);
        return task;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
