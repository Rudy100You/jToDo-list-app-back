package com.rsarabia.jtodolistappback.services;

import com.rsarabia.jtodolistappback.entities.Task;
import com.rsarabia.jtodolistappback.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Async
    public CompletableFuture<Task> createTask(Task task) {
        Task savedTask = taskRepository.save(task);
        return CompletableFuture.completedFuture(savedTask);
    }
}