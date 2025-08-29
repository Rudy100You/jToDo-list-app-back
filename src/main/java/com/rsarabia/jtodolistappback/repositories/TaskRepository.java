package com.rsarabia.jtodolistappback.repositories;

import com.rsarabia.jtodolistappback.entities.DbTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<DbTask, Long> {
}
