package com.andres.planning.data.Tasks;

import java.time.LocalDateTime;

public class TaskSummaryData {

    private static final Long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String category;
    private LocalDateTime dueDate;

    private int priority;
    private boolean completed;

    public TaskSummaryData(String title, String category, LocalDateTime dueDate, int priority) {
        this.title = title;
        this.category = category;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
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

    public String getCategory() {
        return category;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }


    public boolean isCompleted() {
        return completed;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
