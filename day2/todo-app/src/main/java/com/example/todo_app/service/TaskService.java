package com.example.todo_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todo_app.exception.TaskNotFoundException;
import com.example.todo_app.model.Task;
import com.example.todo_app.model.TaskRequest;


@Service
public class TaskService {

    private final Map<Long, Task> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // ✅ 原来的无参：返回全部
    public List<Task> getAll() {
        return new ArrayList<>(store.values());
    }

    // ✅ 新增：带过滤
    public List<Task> getAll(Boolean completed) {
        List<Task> all = getAll();
        if (completed == null) return all;

        return all.stream()
                .filter(t -> t.isCompleted() == completed)
                .collect(Collectors.toList());
    }

    public Task getById(Long id) {
        Task task = store.get(id);
        if (task == null) throw new TaskNotFoundException(id);
        return task;
    }

    public Task create(TaskRequest req) {
        Long id = idGenerator.getAndIncrement();
        Task task = new Task(id, req.getTitle(), req.getDescription(), req.isCompleted());
        store.put(id, task);
        return task;
    }

    public Task update(Long id, TaskRequest req) {
        Task existing = getById(id);
        existing.setTitle(req.getTitle());
        existing.setDescription(req.getDescription());
        existing.setCompleted(req.isCompleted());
        store.put(id, existing);
        return existing;
    }

     public void delete(Long id) {
        Task removed = store.remove(id);
        if (removed == null) throw new TaskNotFoundException(id);
    }
}