package com.andres.planning.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.config.Task;
import org.springframework.test.context.jdbc.Sql;

import com.andres.planning.model.ProjectEntity;
import com.andres.planning.model.TaskEntity;
import com.andres.planning.model.OverlapResult.OverlapResult;
import com.andres.planning.repository.Project.ProjectRepository;
import com.andres.planning.repository.Task.TaskRepository;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = "/clean-data.sql")
public class ProjectTest {

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    @Test
    public void createProject() {
        LocalDateTime time = LocalDateTime.now();
        ProjectEntity project = new ProjectEntity("Project 1", "Description for Project 1", time, time.plusDays(7), "Category 1");

        assertThat(project.getTitle()).isEqualTo("Project 1");
        assertThat(project.getDescription()).isEqualTo("Description for Project 1");
        assertThat(project.getStartTime()).isEqualTo(time);
        assertThat(project.getFinishTime()).isEqualTo(time.plusDays(7));
        assertThat(project.getCategory()).isEqualTo("Category 1");
    }

    @Test
    public void addTaskToProject() {
        LocalDateTime time = LocalDateTime.now();
        ProjectEntity project = new ProjectEntity("Project 1", "Description for Project 1", time, time.plusDays(7), "Category 1");
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1", time, time.plusHours(1), null, 1);

        project.addTask(task, true);

        assertThat(project.containsTask(task)).isTrue();
    }

    @Test
    public void removeTaskFromProject() {
        LocalDateTime time = LocalDateTime.now();
        ProjectEntity project = new ProjectEntity("Project 1", "Description for Project 1", time, time.plusDays(7), "Category 1");
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1", time, time.plusHours(1), null, 1);

        project.addTask(task, true);
        project.removeTask(task);

        assertThat(project.containsTask(task)).isFalse();
    }

    @Test
    public void updateProjectDetails() {
        LocalDateTime time = LocalDateTime.now();
        ProjectEntity project = new ProjectEntity("Project 1", "Description for Project 1", time, time.plusDays(7), "Category 1");
        projectRepository.save(project);

        project.setTitle("Updated Project 1");
        project.setDescription("Updated Description for Project 1");
        project.setCategory("Updated Category 1");
        projectRepository.save(project);

        ProjectEntity updatedProject = projectRepository.findById(project.getId()).orElse(null);
        assertThat(updatedProject).isNotNull();
        assertThat(updatedProject.getTitle()).isEqualTo("Updated Project 1");
        assertThat(updatedProject.getDescription()).isEqualTo("Updated Description for Project 1");
        assertThat(updatedProject.getCategory()).isEqualTo("Updated Category 1");
    }

    @Test
    public void checkOverlapTask() {
        LocalDateTime time = LocalDateTime.now();
        ProjectEntity project = new ProjectEntity("Project 1", "Description for Project 1", time, time.plusDays(7), "Category 1");
        TaskEntity task1 = new TaskEntity("Task 1", "Description for Task 1", time, time.plusHours(1), null, 1);
        TaskEntity task2 = new TaskEntity("Task 2", "Description for Task 2", time.plusMinutes(30), time.plusHours(2), null, 1);

        OverlapResult overlapResult = project.addTask(task1, false);
        assertThat(overlapResult.isOverlap()).isFalse();
        assertThat(project.getTasks()).contains(task1);

        overlapResult = project.addTask(task2, false); 
        assertThat(overlapResult.isOverlap()).isTrue(); // User will be adviced for the overlap
        assertThat(project.getTasks()).doesNotContain(task2);
        assertThat(overlapResult.getConflictingTasks()).contains(task1);

        overlapResult = project.addTask(task2, true); // Ignore overlap
        assertThat(project.getTasks()).contains(task1, task2);

    }
    
}
