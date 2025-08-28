package com.andres.planning.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "projects")
public class ProjectEntity extends PlanningItemEntity {


    @OneToMany(mappedBy = "project")
    private Set<TaskEntity> tasks = new HashSet<>();


    public ProjectEntity(String title, String description, LocalDateTime startTime, LocalDateTime finishTime, String category) {
        super(title, description, startTime, finishTime, category);
    }

    public ProjectEntity() {}


    public boolean addTask(TaskEntity task, boolean notify) {
        if(!containsTask(task) || !notify) return false;

        return this.tasks.add(task);
    }

    public boolean overlapsWith(TaskEntity other) {
        Boolean overlap = false;

        for (TaskEntity subTask : this.tasks) {
            if(overlap = (subTask.getStartTime().isBefore(other.getFinishTime()) 
                && subTask.getFinishTime().isAfter(other.getStartTime()))) break;
            
        }
        return overlap;
    }

    public boolean containsTask(TaskEntity task) {
        return this.tasks.contains(task);
    }

    public boolean removeTask(TaskEntity task) {
        return this.tasks.remove(task);
    }

    // Getters and Setters


    public Set<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public TaskEntity getTask(String title) {
        for (TaskEntity task : tasks) {
            if (task.getTitle().equals(title)) {
                return task;
            }
        }
        return null;
    }

    public TaskEntity getTask(Long id) {
        for (TaskEntity task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }
}
