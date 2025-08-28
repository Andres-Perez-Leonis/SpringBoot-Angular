package com.andres.planning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@lombok.EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "events")
public class EventEntity extends PlanningItemEntity {

    private String location;

    // Getters y Setters

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
