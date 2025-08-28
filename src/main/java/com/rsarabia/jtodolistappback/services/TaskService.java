package com.rsarabia.jtodolistappback.services;

import com.rsarabia.jtodolistappback.entities.Task;
import com.rsarabia.jtodolistappback.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Async
    public CompletableFuture<Task> getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return CompletableFuture.completedFuture(task.orElseThrow(EntityNotFoundException::new));
    }

    @Async
    public CompletableFuture<List<Task>> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return CompletableFuture.completedFuture(tasks);
    }
}