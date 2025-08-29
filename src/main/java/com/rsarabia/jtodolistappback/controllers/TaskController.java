package com.rsarabia.jtodolistappback.controllers;

import com.rsarabia.jtodolistappback.dto.task.TaskResponseDto;
import com.rsarabia.jtodolistappback.dto.task.TaskToCreateDto;
import com.rsarabia.jtodolistappback.dto.task.TaskToPatchDto;
import com.rsarabia.jtodolistappback.entities.DbTask;
import com.rsarabia.jtodolistappback.mappers.TaskMapper;
import com.rsarabia.jtodolistappback.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<TaskResponseDto>> createTask(@RequestBody @Valid TaskToCreateDto taskDto) {
        DbTask dbTask = new DbTask();
        taskMapper.createFromDto(taskDto, dbTask);
        return taskService.createTask(dbTask).thenApply(savedTask -> {
            TaskResponseDto savedDto = taskMapper.toDto(savedTask);
            URI location = URI.create(String.format("/api/v1/tasks/%d", savedDto.getId()));
            return ResponseEntity.created(location).body(savedDto);
        });
    }

    @Async
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<TaskResponseDto>> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .thenApply(taskMapper::toDto)
                .thenApply(ResponseEntity::ok);
    }

    @Async
    @GetMapping
    public CompletableFuture<ResponseEntity<Iterable<TaskResponseDto>>> getTasks() {
        return taskService.getTasks()
                .thenApply(taskMapper::toResponseDtoList)
                .thenApply(ResponseEntity::ok);
    }

    @Async
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<TaskResponseDto>> updateTask(@PathVariable Long id, @RequestBody @Valid TaskToCreateDto taskDto) {
        return taskService.updateTask(id, taskDto)
                .thenApply(taskMapper::toDto)
                .thenApply(ResponseEntity::ok);
    }

    @Async
    @PatchMapping("/{id}")
    public CompletableFuture<ResponseEntity<TaskResponseDto>> patchTask(@PathVariable Long id, @RequestBody TaskToPatchDto taskDto) {
        return taskService.patchTask(id, taskDto)
                .thenApply(taskMapper::toDto)
                .thenApply(ResponseEntity::ok);
    }

    @Async
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id)
                .thenApply(unused -> ResponseEntity.noContent().build());
    }
}