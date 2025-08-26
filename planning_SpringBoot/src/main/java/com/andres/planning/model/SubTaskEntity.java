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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class SubTaskEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    private String description;

    @NonNull
    private Boolean completed = false;

    @NonNull
    private int priority;

    @NonNull
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @NonNull
    private TaskEntity taskID;

    public SubTaskEntity() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public TaskEntity getTaskID() {
        return taskID;
    }

    public void setTaskID(TaskEntity taskID) {
        this.taskID = taskID;
    }

}
