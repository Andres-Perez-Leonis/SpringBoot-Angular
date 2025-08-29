package com.andres.planning.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
public abstract class PlanningItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    private String title;
    private String description;

    @NonNull
    @Column(name = "start_time")
    //@Temporal(TemporalType.DATE)
    private LocalDateTime startTime;


    @NonNull
    @Column(name = "finish_time")
    //@Temporal(TemporalType.DATE)
    private LocalDateTime finishTime;


    private String category;

    public PlanningItemEntity() {}

    public PlanningItemEntity(@NonNull String title, String description, @NonNull LocalDateTime startTime,
            @NonNull LocalDateTime finishTime, String category) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
