package com.rsarabia.jtodolistappback.dto.task;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime completedAt;
}
