package com.andres.planning.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.lang.NonNull;

import com.andres.planning.model.OverlapResult.OverlapResult;

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

    public TaskEntity(@NonNull String name, String description,@NonNull Boolean completed, @Nonnull LocalDateTime startTime, @Nonnull LocalDateTime finishTime, String category, int priority) {
        super(name, description, startTime, finishTime, category);
        this.priority = priority;
        this.completed = completed != null ? completed : false;
    }

    //Methods



    public OverlapResult overlapsWith(SubTaskEntity other) {

        OverlapResult result = new OverlapResult(false);

        for (SubTaskEntity subTask : this.subTasks) {
            if (subTask.getStartTime().isBefore(other.getFinishTime())
                    && subTask.getFinishTime().isAfter(other.getStartTime())) {
                result.setOverlap(true);
                result.addOverlap(subTask);
            }
        }
        return result;
    }

    public OverlapResult addSubTask(SubTaskEntity subTask, boolean notifyUser) {

        OverlapResult overlapResult = overlapsWith(subTask);
        if (overlapResult.isOverlap() && notifyUser) {
            // Notify user about overlap
            return overlapResult;
        }

        subTask.setTaskID(this.getId());
        this.subTasks.add(subTask);
        return overlapResult;
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
