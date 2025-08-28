package com.andres.planning.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.andres.planning.model.ProjectEntity;
import com.andres.planning.model.SubTaskEntity;
import com.andres.planning.model.TaskEntity;
import com.andres.planning.repository.Project.ProjectRepository;
import com.andres.planning.repository.SubTask.SubTaskRepository;
import com.andres.planning.repository.Task.TaskRepository;

import jakarta.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@Sql(scripts = "/clean-data.sql")
public class TaskTest {
    
    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;

    private EntityManager entityManager;
    private SubTaskRepository subTaskRepository;


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

        task.setProjectId(project.getId());

        assertThat(task.getProjectId()).isEqualTo(project.getId());
        assertThat(project.containsTask(task)).isTrue();
    }


    @Test
    public void checkTaskOverlap() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task1 = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);

        SubTaskEntity subTask1 = new SubTaskEntity("SubTask 1", "Description for SubTask 1", false, 1,
                time, time.plusMinutes(30));
        SubTaskEntity subTask2 = new SubTaskEntity("SubTask 2", "Description for SubTask 2", false, 2,
                time.plusMinutes(30), time.plusHours(1));

        task1.addSubTask(subTask1, false); // No overlap

        boolean notify = false;
        boolean overlap = task1.addSubTask(subTask2, false); // Overlap
        if(overlap) notify = true; // User notified about overlap
        assertThat(notify).isTrue();
        task1.addSubTask(subTask2, notify); // ignore overlap
        assertThat(task1.getSubTasks()).contains(subTask1, subTask2);
    }


    @Test
    public void checkTaskNoOverlap() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task1 = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);

        SubTaskEntity subTask1 = new SubTaskEntity("SubTask 1", "Description for SubTask 1", false, 1,
                time, time.plusMinutes(30));
        SubTaskEntity subTask2 = new SubTaskEntity("SubTask 2", "Description for SubTask 2", false, 2,
                time.plusMinutes(30), time.plusHours(1));

        task1.addSubTask(subTask1, false); // No overlap

        boolean notify = false;
        boolean overlap = task1.addSubTask(subTask2, false); // No Overlap
        if(overlap) notify = true; // User notified about overlap
        assertThat(notify).isFalse(); // No notification should be sent
        assertThat(task1.getSubTasks()).contains(subTask1, subTask2);
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

        task.setProjectId(project.getId());


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


    @Test
    @Transactional
    public void modifyTask() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);
        taskRepository.save(task);

        task.setTitle("Updated Task 1");
        taskRepository.save(task);

        TaskEntity updatedTask = taskRepository.findById(task.getId()).orElse(null);
        assertThat(updatedTask).isNotNull();
        assertThat(updatedTask.getTitle()).isEqualTo("Updated Task 1");
    }


    @Test
    @Transactional
    public void completeTask() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);
        taskRepository.save(task);

        task.setCompleted(true);
        taskRepository.save(task);

        TaskEntity completedTask = taskRepository.findById(task.getId()).orElse(null);
        assertThat(completedTask).isNotNull();
        assertThat(completedTask.getCompleted()).isTrue();
    }


    @Test
    @Transactional
    public void modifyProjectTask() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);
        taskRepository.save(task);

        ProjectEntity project = new ProjectEntity("Project 1", "Description for Project 1",
                time, time.plusDays(7), "Category 1");
        project.addTask(task, true);
        projectRepository.save(project);

        TaskEntity projectTask = project.getTask(task.getId());
        projectTask.setTitle("Task 2");
        taskRepository.save(projectTask);

        // Forzar escritura y limpiar el contexto de persistencia
        entityManager.flush();
        entityManager.clear();

        ProjectEntity updatedProjectDB = projectRepository.findById(project.getId()).orElseThrow();
        TaskEntity updatedProjectTask = updatedProjectDB.getTask(task.getId());

        assertThat(updatedProjectTask).isNotNull();
        assertThat(updatedProjectTask.getTitle()).isEqualTo("Task 2");
    }


    @Test
    @Transactional
    public void deleteTask() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);
        taskRepository.save(task);

        taskRepository.delete(task);

        TaskEntity deletedTask = taskRepository.findById(task.getId()).orElse(null);
        assertThat(deletedTask).isNull();
    }

    @Test
    @Transactional
    public void addSubTask() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);
        taskRepository.save(task);

        SubTaskEntity subTask = new SubTaskEntity("SubTask 1", "Description for SubTask 1", false, 1,
                time, time.plusHours(1));
        subTaskRepository.save(subTask);

        task.addSubTask(subTask, true);
        subTask.setTaskID(task.getId());

        assertThat(subTask.getTaskID()).isEqualTo(task);
        assertThat(task.getSubTasks()).contains(subTask);

    }

    @Test
    @Transactional
    public void deleteSubTask() {
        LocalDateTime time = LocalDateTime.now();
        TaskEntity task = new TaskEntity("Task 1", "Description for Task 1",
                time, time.plusHours(1), null, 1);
        taskRepository.save(task);

        SubTaskEntity subTask = new SubTaskEntity("SubTask 1", "Description for SubTask 1", false, 1,
                time, time.plusHours(1));
        subTask.setTaskID(task.getId());
        subTaskRepository.save(subTask);

        // Verificamos que existe
        assertThat(subTaskRepository.findById(subTask.getId())).isPresent();

        // Eliminamos
        subTaskRepository.delete(subTask);
        task.removeSubTask(subTask);

        // Forzamos escritura y recargamos
        entityManager.flush();
        entityManager.clear();

        // Verificamos que ya no existe en la BD
        assertThat(subTaskRepository.findById(subTask.getId())).isNotPresent();

        assertThat(task.getSubTasks()).doesNotContain(subTask);
    }
    
}