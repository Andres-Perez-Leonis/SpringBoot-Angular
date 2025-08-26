package com.andres.planning.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class ProjectEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    private String title;
    private String description;

    @NonNull
    @Column(name = "due_time")
    @Temporal(TemporalType.DATE)
    private LocalDateTime dueTime;
    private String category;

    @OneToMany(mappedBy = "project")
    private Set<TaskEntity> tasks = new HashSet<>();



    public ProjectEntity() {}


    public boolean addTask(TaskEntity task, boolean notify) {
        if(this.tasks.contains(task) && !notify) return false;

        return this.tasks.add(task);
    }

    public boolean containsTask(TaskEntity task) {
        return this.tasks.contains(task);
    }

    public boolean removeTask(TaskEntity task) {
        return this.tasks.remove(task);
    }

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

    public LocalDateTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
