package com.andres.planning.data.Projects;

import java.time.LocalDateTime;

public class ProjectSummaryData {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String category;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
