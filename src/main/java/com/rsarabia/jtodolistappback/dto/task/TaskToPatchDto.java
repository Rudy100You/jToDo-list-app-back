package com.rsarabia.jtodolistappback.dto.task;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskToPatchDto {
    @Size(min = 1, message = "title must not be empty")
    private String title;

    @Size(min = 1, message = "description must not be empty")
    private String description;

    private boolean completed = false;
}