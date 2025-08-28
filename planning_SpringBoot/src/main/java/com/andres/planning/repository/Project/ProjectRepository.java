package com.andres.planning.repository.Project;

import com.andres.planning.model.ProjectEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long>{
    public ProjectEntity findByTitle(String title);
    public List<ProjectEntity> findByCategory(String category);
}
