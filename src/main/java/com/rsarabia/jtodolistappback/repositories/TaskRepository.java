package com.rsarabia.jtodolistappback.repositories;

import com.rsarabia.jtodolistappback.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
