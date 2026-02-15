package com.example.todoapp.task;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    record CreateTaskRequest(String title) {}
    record UpdateTaskRequest(String title, Boolean completed) {}

    @PostMapping
    public Task create(@RequestBody CreateTaskRequest req) {
        return service.create(req.title());
    }

    @GetMapping
    public List<Task> list() {
        return service.list();
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody UpdateTaskRequest req) {
        return service.update(id, req.title(), req.completed());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
