package com.rsarabia.jtodolistappback.controllers;

import com.rsarabia.jtodolistappback.entities.Task;
import com.rsarabia.jtodolistappback.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Task>> createTask(@RequestBody Task task) {
        return taskService.createTask(task).thenApply(savedTask -> {
            URI location = URI.create(String.format("/api/tasks/%d", savedTask.getId()));
            return ResponseEntity.created(location).body(savedTask);
        });
    }

    @Async
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Task>> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id).thenApply(ResponseEntity::ok);
    }

    @Async
    @GetMapping
    public CompletableFuture<ResponseEntity<Iterable<Task>>> getTasks() {
        return taskService.getTasks().thenApply(ResponseEntity::ok);
    }
}