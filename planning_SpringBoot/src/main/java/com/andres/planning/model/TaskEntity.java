package com.andres.planning.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tasks")
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    private String title;

    @Nonnull
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private LocalDateTime dueDate;

    private String category;


    private int priority;

    private String description;

    private Boolean completed = false;

    @ManyToOne
    @Nonnull
    @JoinColumn(name = "project_id")
    private ProjectEntity projectId;

    @OneToMany(mappedBy = "task")
    Set<SubTaskEntity> subTasks = new HashSet<>();


    public TaskEntity() {}

    //Methods



    public boolean overlapsWith(TaskEntity other) {
        Boolean overlap = false;

        for (SubTaskEntity subTask : this.subTasks) {
        }
        return overlap;
    }

    public boolean addSubTask(SubTaskEntity subTask) {
        return this.subTasks.add(subTask);
    }

    public boolean removeSubTask(SubTaskEntity subTask) {
        return this.subTasks.remove(subTask);
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

    public ProjectEntity getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectEntity projectId) {
        this.projectId = projectId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public Set<SubTaskEntity> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Set<SubTaskEntity> subTasks) {
        this.subTasks = subTasks;
    }
}
