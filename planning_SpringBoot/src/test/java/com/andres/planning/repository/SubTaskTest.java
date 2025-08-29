package com.andres.planning.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.andres.planning.model.SubTaskEntity;
import com.andres.planning.model.TaskEntity;
import com.andres.planning.repository.SubTask.SubTaskRepository;
import com.andres.planning.repository.Task.TaskRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Sql(scripts = "./../../resources/clean-data.sql")
@ActiveProfiles("test")
public class SubTaskTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubTaskRepository subTaskRepository;

    @Test
    public void createSubTask() {
        SubTaskEntity subTask = new SubTaskEntity("SubTask 1", "Description for SubTask 1", false, 1,
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));

        //assertThat(subTask.getId()).isNotNull();
        assertThat(subTask.getTitle()).isEqualTo("SubTask 1");
        assertThat(subTask.getDescription()).isEqualTo("Description for SubTask 1");
        assertThat(subTask.getStartTime()).isNotNull();
        assertThat(subTask.getFinishTime()).isNotNull();
    }

    @Test
    public void updateSubTask() {
        SubTaskEntity subTask = new SubTaskEntity("SubTask 1", "Description for SubTask 1", false, 1,
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
        subTask.setTitle("Updated SubTask 1");
        subTask.setDescription("Updated Description for SubTask 1");

        //assertThat(subTask.getId()).isNotNull();
        assertThat(subTask.getTitle()).isEqualTo("Updated SubTask 1");
        assertThat(subTask.getDescription()).isEqualTo("Updated Description for SubTask 1");
        assertThat(subTask.getStartTime()).isNotNull();
        assertThat(subTask.getFinishTime()).isNotNull();
    }

    @Test
    public void deleteSubTask() {
        SubTaskEntity subTask = new SubTaskEntity("SubTask 1", "Description for SubTask 1", false, 1,
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
        subTaskRepository.save(subTask);
        subTaskRepository.delete(subTask);
        assertThat(subTaskRepository.findById(subTask.getId())).isEmpty();
    }

    @Test
    public void SubTaskAssigned() {
        // Create a SubTask and Assign to a Task
        SubTaskEntity subTask = new SubTaskEntity("SubTask 1", "Description for SubTask 1", false, 1,
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
        subTaskRepository.save(subTask);

        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1", false, LocalDateTime.now(),
                LocalDateTime.now().plusHours(1), null, 1);
        taskRepository.save(task);

        subTask.setTask(task);
        subTaskRepository.save(subTask);

        assertThat(subTask.getTask()).isEqualTo(task);
    }

    @Test
    @Transactional
    public void findSubTaskById() {
        SubTaskEntity subTask = new SubTaskEntity("SubTask 1", "Description for SubTask 1", false, 1,
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
        subTaskRepository.save(subTask);
        assertThat(subTaskRepository.findById(subTask.getId())).isPresent();
        assertThat(subTaskRepository.findById(subTask.getId()).get().getTitle()).isEqualTo("SubTask 1");
    }
}
