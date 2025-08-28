package com.andres.planning.repository.Task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andres.planning.model.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
    public TaskEntity findByTitle(String title);
    public List<TaskEntity> findByStatus(String status);
    public List<TaskEntity> findByCategory(String category);
    public List<TaskEntity> findByCategoryAndStatus(String category, Boolean status);
    public List<TaskEntity> findByProjectAndCategoryAndStatus(Long projectId, String category, Boolean status);
    public List<TaskEntity> findByCategoryAndStatusAndDateAfterAndDateBefore(String category, Boolean status, LocalDateTime dateAfter, LocalDateTime dateBefore);
    public List<TaskEntity> findByProjectAndCategoryAndStatusAndDateAfterAndDateBefore(Long projectId, String category, Boolean status, LocalDateTime dateAfter, LocalDateTime dateBefore);
    public List<TaskEntity> findByProjectId(Long projectId);
}
