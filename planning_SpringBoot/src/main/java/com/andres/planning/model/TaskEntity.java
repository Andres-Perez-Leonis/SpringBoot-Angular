package com.andres.planning.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class TaskEntity extends PlanningItemEntity {



    private int priority;

    private Boolean completed = false;

    @ManyToOne
    @Nonnull
    @JoinColumn(name = "project_id")
    private Long projectId;

    @OneToMany(mappedBy = "task")
    Set<SubTaskEntity> subTasks = new HashSet<>();


    public TaskEntity() {}

    public TaskEntity(String name, String description, LocalDateTime startTime, LocalDateTime finishTime, String category, int priority) {
        super(name, description, startTime, finishTime, category);
        this.priority = priority;
    }

    //Methods



    public boolean overlapsWith(SubTaskEntity other) {
        Boolean overlap = false;

        for (SubTaskEntity subTask : this.subTasks) {
            if(overlap = (subTask.getStartTime().isBefore(other.getFinishTime()) 
                && subTask.getFinishTime().isAfter(other.getStartTime()))) break;
            
        }
        return overlap;
    }

    public boolean addSubTask(SubTaskEntity subTask, boolean notifyUser) {
        if (notifyUser && overlapsWith(subTask)) {
            // Notify user about overlap
            return false;
        }
        subTask.setTaskID(this.getId());
        return this.subTasks.add(subTask);
    }

    public boolean removeSubTask(SubTaskEntity subTask) {
        subTask.setTaskID(null);
        return this.subTasks.remove(subTask);
    }


    // Getters and Setters

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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
