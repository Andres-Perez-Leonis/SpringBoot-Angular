package com.andres.planning.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "sub_tasks")
public class SubTaskEntity extends TaskEntity{
  

    @ManyToOne
    @JoinColumn(name = "task_id")
    @NonNull
    private Long taskID;

    public SubTaskEntity() {}

    public SubTaskEntity(@NonNull String title, String description, @NonNull Boolean completed,
            @NonNull int priority, @NonNull LocalDateTime startTime, @NonNull LocalDateTime finishTime) {
        super(title, description, completed, startTime, finishTime, null, priority);
    }

    // Getters and Setters


    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

}
