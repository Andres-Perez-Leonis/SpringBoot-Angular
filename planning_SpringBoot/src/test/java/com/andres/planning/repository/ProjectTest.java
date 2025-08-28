package com.andres.planning.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.config.Task;
import org.springframework.test.context.jdbc.Sql;

import com.andres.planning.model.ProjectEntity;
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


}
