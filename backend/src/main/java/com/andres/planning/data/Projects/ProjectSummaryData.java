package com.andres.planning.data.Projects;

import java.time.LocalDateTime;

public class ProjectSummaryData {
    protected String title;
    protected String description;
    protected LocalDateTime dueDate;
    protected String category;

    public ProjectSummaryData(String title, String description, String category, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
    }

    public ProjectSummaryData(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    
}
