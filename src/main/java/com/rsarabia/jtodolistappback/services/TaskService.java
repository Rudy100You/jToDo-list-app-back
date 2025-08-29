package com.rsarabia.jtodolistappback.services;

import com.rsarabia.jtodolistappback.dto.task.TaskToCreateDto;
import com.rsarabia.jtodolistappback.dto.task.TaskToPatchDto;
import com.rsarabia.jtodolistappback.entities.DbTask;
import com.rsarabia.jtodolistappback.mappers.TaskMapper;
import com.rsarabia.jtodolistappback.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Async
    public CompletableFuture<DbTask> createTask(DbTask dbTask) {
        DbTask savedDbTask = taskRepository.save(dbTask);
        return CompletableFuture.completedFuture(savedDbTask);
    }

    @Async
    public CompletableFuture<DbTask> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(CompletableFuture::completedFuture)
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
    }

    @Async
    public CompletableFuture<List<DbTask>> getTasks() {
        List<DbTask> dbTasks = taskRepository.findAll();
        return CompletableFuture.completedFuture(dbTasks);
    }

    @Async
    public CompletableFuture<DbTask> updateTask(Long id, TaskToCreateDto taskDto) {
        return this.getTaskById(id).thenApply(existingTask -> {
            taskMapper.createFromDto(taskDto, existingTask);
            return taskRepository.save(existingTask);
        });
    }

    @Async
    public CompletableFuture<DbTask> patchTask(Long id, TaskToPatchDto taskDto) {
        return this.getTaskById(id).thenApply(existingTask -> {
            taskMapper.patchFromDto(taskDto, existingTask);
            return taskRepository.save(existingTask);
        });
    }

    @Async
    public CompletableFuture<Void> deleteTask(Long id) {
        return this.getTaskById(id).thenAccept(taskRepository::delete);
    }
}