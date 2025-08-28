package com.andres.planning.model.OverlapResult;

import java.util.ArrayList;
import java.util.List;

import com.andres.planning.model.TaskEntity;

public class OverlapResult {
    private boolean overlap;
    private String message;
    private List<TaskEntity> conflictingTasks;

    public OverlapResult(boolean overlap, String message) {
        this.overlap = overlap;
        this.message = message;
        this.conflictingTasks = new ArrayList<>();
    }
    public OverlapResult(boolean overlap) {
        this.overlap = overlap;
        this.conflictingTasks = new ArrayList<>();
    }

    public OverlapResult(){}

    public void addOverlap(TaskEntity task) {
        this.conflictingTasks.add(task);
    }

    public List<TaskEntity> getConflictingTasks() {
        return conflictingTasks;
    }

    public void setConflictingTasks(List<TaskEntity> conflictingTasks) {
        this.conflictingTasks = conflictingTasks;
    }

    public boolean isOverlap() {
        return overlap;
    }

    public void setOverlap(boolean overlap) {
        this.overlap = overlap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
