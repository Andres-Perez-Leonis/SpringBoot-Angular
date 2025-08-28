package com.andres.planning.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.andres.planning.model.ProjectEntity;
import com.andres.planning.model.TaskEntity;
import com.andres.planning.repository.Project.ProjectRepository;
import com.andres.planning.repository.Task.TaskRepository;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Sql(scripts = "/clean-data.sql")
public class TaskTest {
    
    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;



    @Test
    public void createTask() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1", 
        time, time.plusHours(1), null, 1);

        assertThat(task.getTitle()).isEqualTo("Task 1");
        assertThat(task.getDescription()).isEqualTo("Description for Task 1");
        assertThat(task.getStartTime()).isEqualTo(time);
        assertThat(task.getFinishTime()).isEqualTo(time.plusHours(1));
        assertThat(task.getCategory()).isNull();
        assertThat(task.getPriority()).isEqualTo(1);
    }


    @Test
    public void TaskAssigned() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1", 
        time, time.plusHours(1), null, 1);

        ProjectEntity project = new ProjectEntity("Project 1", "Description for Project 1", time, time.plusDays(7), "Category 1");

        project.addTask(task,false); // User will be notified that task overlaps

        project.addTask(task, true); // User said to ignore the overlap

        task.setProjectId(project);


        assertThat(task.getProjectId()).isEqualTo(project);
        assertThat(project.containsTask(task)).isTrue();
    }


    @Test
    public void checkTaskOverlap() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task1 = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);
        TaskEntity task2 = new TaskEntity("Task 2", "Description for Task 2",
                time.plusMinutes(30), time.plusHours(2), null, 2);

        assertThat(task1.overlapsWith(task2)).isTrue();
    }


    @Test
    public void checkTaskNoOverlap() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task1 = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);
        TaskEntity task2 = new TaskEntity("Task 2", "Description for Task 2",
                time.plusHours(1), time.plusHours(2), null, 2);

        assertThat(task1.overlapsWith(task2)).isFalse();
    }


    @Test
    @Transactional
    public void saveTaskInBD() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);
        taskRepository.save(task);

        assertThat(task.getId()).isNotNull();

        TaskEntity taskBD = taskRepository.findById(task.getId()).orElse(null);
        assertThat(taskBD).isNotNull();
        assertThat(taskBD.getTitle()).isEqualTo("Task 1");
    }

    @Test
    @Transactional
    public void saveTaskWithProject() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);

        ProjectEntity project = new ProjectEntity("Project 1", "Description for Project 1", time, time.plusDays(7), "Category 1");

        project.addTask(task, true); // User said to ignore the overlap

        task.setProjectId(project);


        projectRepository.save(project);
        taskRepository.save(task);

        assertThat(task.getId()).isNotNull();
        assertThat(project.getId()).isNotNull();

        TaskEntity taskBD = taskRepository.findById(task.getId()).orElse(null);
        assertThat(taskBD).isNotNull();
        assertThat(taskBD.getTitle()).isEqualTo("Task 1");
        assertThat(taskBD.getProjectId()).isEqualTo(project);
        assertThat(project.containsTask(taskBD)).isTrue();
    }


}
