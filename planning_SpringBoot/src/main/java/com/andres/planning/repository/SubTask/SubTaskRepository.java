package com.andres.planning.repository.SubTask;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andres.planning.model.SubTaskEntity;

public interface SubTaskRepository extends JpaRepository<SubTaskEntity, Long>{
    
}
