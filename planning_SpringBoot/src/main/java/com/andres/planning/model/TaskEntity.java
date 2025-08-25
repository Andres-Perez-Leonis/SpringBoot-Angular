package com.andres.planning.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.andres.planning.data.SubTaks.SubTaskData;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    private LocalDate dueDate;

    private String category;
    
    private int priority;

    private String description;

    private Boolean completed;

    @OneToMany(mappedBy = "task")
    Set<SubTaskEntity> subTasks = new HashSet<>();

    //Methods



    public boolean overlapsWith(TaskEntity other) {
        Boolean overlap = false;

        for (SubTaskEntity subTask : this.subTasks) {
            if (other.getSubTasks().contains(subTask)) {
                overlap = true;
                break;
            }
        }
        return overlap;
    }

    public void addSubTask(SubTaskData subTask) {
        this.subTasks.add(subTask);
    }

    public void removeSubTask(SubTaskData subTask) {
        this.subTasks.remove(subTask);
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
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

    public Set<SubTaskData> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Set<SubTaskData> subTasks) {
        this.subTasks = subTasks;
    }
}
