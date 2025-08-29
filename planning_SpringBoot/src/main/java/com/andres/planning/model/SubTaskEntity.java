package com.andres.planning.model;

import java.time.LocalDateTime;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sub_tasks")
public class SubTaskEntity extends TaskEntity{
  

    @ManyToOne
    @JoinColumn(name = "task_id")
    @NonNull
    private TaskEntity task;

    public SubTaskEntity() {}

    public SubTaskEntity(@NonNull String title, String description, @NonNull Boolean completed,
            @NonNull int priority, @NonNull LocalDateTime startTime, @NonNull LocalDateTime finishTime) {
        super(title, description, completed, startTime, finishTime, null, priority);
    }

    // Getters and Setters


    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

}
