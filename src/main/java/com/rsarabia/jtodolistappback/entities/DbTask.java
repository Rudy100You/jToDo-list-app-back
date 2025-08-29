package com.rsarabia.jtodolistappback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class DbTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private boolean completed = false;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private ZonedDateTime completedAt = null;

    @PrePersist
    protected void onCreate() {
        createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
        updatedAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public void setCompleted(boolean completed) {
        if (completed && !this.completed) {
            this.completedAt = ZonedDateTime.now(ZoneId.of("UTC"));
        }
        else if (!completed && this.completed) {
            this.completedAt = null;
        }
        this.completed = completed;
    }
}