package com.andres.planning.data.SubTaks;

import java.time.LocalDateTime;


public class SubTaskData{

    protected String title;
    protected String category;
    protected LocalDateTime dueDate;

    protected int priority;
    protected boolean completed;


    public SubTaskData(String title, String category, LocalDateTime dueDate, int priority) {
        this.title = title;
        this.category = category;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
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
