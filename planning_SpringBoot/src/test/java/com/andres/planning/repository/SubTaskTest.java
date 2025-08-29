package com.andres.planning.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.andres.planning.model.SubTaskEntity;
import com.andres.planning.repository.SubTask.SubTaskRepository;

@SpringBootTest
@Sql(scripts = "/clean-data.sql")
public class SubTaskTest {
    private SubTaskRepository subTaskRepository;

    @Test
    public void createSubTask() {
        SubTaskEntity subTask = new SubTaskEntity("SubTask 1", "Description for SubTask 1", false, 1,
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));

        assertThat(subTask.getId()).isNotNull();
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

        assertThat(subTask.getId()).isNotNull();
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
}
