package com.andres.planning.data.Projects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.andres.planning.data.Tasks.TaskData;

public class ProjectData {
    private ProjectSummaryData summary;
    private List<TaskData> tasks;

    public ProjectData(String title, String description, String category) {
        this.summary = new ProjectSummaryData(title, description, category, null);
        this.tasks = new ArrayList<>();
    }

    public ProjectData(String title, String description, String category, LocalDateTime dueDate) {
        this.summary = new ProjectSummaryData(title, description, category, dueDate);
        this.tasks = new ArrayList<>();
    }

    public ProjectSummaryData getSummary() {
        return summary;
    }

    public void setSummary(ProjectSummaryData summary) {
        this.summary = summary;
    }

    public List<TaskData> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskData> tasks) {
        this.tasks = tasks;
    }
}
