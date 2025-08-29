package com.rsarabia.jtodolistappback.dto.task;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskToCreateDto {
    @NotBlank(message = "The title is required")
    private String title;

    @NotBlank(message = "The description is required")
    private String description;

    private boolean completed = false;
}