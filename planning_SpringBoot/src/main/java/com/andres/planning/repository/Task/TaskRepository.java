package com.andres.planning.repository.Task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andres.planning.model.ProjectEntity;
import com.andres.planning.model.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
    public TaskEntity findByTitle(String title);
    public List<TaskEntity> findByCompleted(Boolean completed);
    public List<TaskEntity> findByCategory(String category);
    public List<TaskEntity> findByCategoryAndCompleted(String category, Boolean completed);
    public List<TaskEntity> findByProjectAndCategoryAndCompleted(ProjectEntity projectEntity, String category, Boolean completed);
    public List<TaskEntity> findByCategoryAndCompletedAndStartTimeAndFinishTime(String category, Boolean completed, LocalDateTime startTime, LocalDateTime finishTime);
    public List<TaskEntity> findByProjectAndCategoryAndCompletedAndStartTimeAndFinishTime(ProjectEntity projectEntity, String category, Boolean completed, LocalDateTime startTime, LocalDateTime finishTime);
    public List<TaskEntity> findByProject(ProjectEntity projectEntity);
}
