package com.andres.planning.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.andres.planning.model.TaskEntity;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Sql(scripts = "/clean-data.sql")
public class TaskTest {
    
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

}
