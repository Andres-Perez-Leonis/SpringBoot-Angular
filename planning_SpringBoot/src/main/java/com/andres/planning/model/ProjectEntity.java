package com.andres.planning.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


import com.andres.planning.model.OverlapResult.OverlapResult;

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


    public OverlapResult addTask(TaskEntity task, boolean notify) {
        OverlapResult overlapResult = overlapsWith(task);
        if (overlapResult.isOverlap() && !notify) {
            // Notify user about overlap
            return overlapResult;
        }

        task.setProject(this);
        this.tasks.add(task);
        return overlapResult;
    }

    public OverlapResult overlapsWith(TaskEntity other) {
        OverlapResult result = new OverlapResult(false);

        for (TaskEntity task : this.tasks) {
            if (task.getStartTime().isBefore(other.getFinishTime())
                    && task.getFinishTime().isAfter(other.getStartTime())) {
                result.setOverlap(true);
                result.addOverlap(task);
            }
        }
        return result;
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
