package fr.esgi.taskmanager.infra;

import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.infra.repository.JsonTaskRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
public class JsonTaskRepositoryTest {

    @Test
    public void add_task_in_file() throws IOException {
        // Given
        JsonTaskRepository jsonTaskRepository = new JsonTaskRepository("tasks.json");
        // When
        Task basicTask = new Task("content 7", LocalDate.now());
        var taskSaved = jsonTaskRepository.save(basicTask);
        var taskFound = jsonTaskRepository.findById(taskSaved.getId());
        // Then
        assert taskFound.isPresent();
        Assertions.assertEquals(taskSaved, taskFound.get());
//        jsonTaskRepository.delete(taskSaved.getId());

    }

    @Test
    public void delete_task_in_file() throws IOException {
        // Given
        JsonTaskRepository jsonTaskRepository = new JsonTaskRepository("tasks.json");
        // When
        Task basicTask = new Task("content to erase", LocalDate.now());
        var taskSaved = jsonTaskRepository.save(basicTask);

        var lastTaskStanding = jsonTaskRepository.findById(taskSaved.getId());
        assert lastTaskStanding.isPresent();

        jsonTaskRepository.delete(lastTaskStanding.get().getId());

        // Then
        assert !jsonTaskRepository.findAll().contains(lastTaskStanding.get());


    }
    @Test
    public void modify_task_in_file() throws IOException {
        // Given
        JsonTaskRepository jsonTaskRepository = new JsonTaskRepository("tasks.json");
        // When
        var lastTaskToModify = jsonTaskRepository.findAll().stream().reduce((first, second) -> second).get();
        lastTaskToModify.setContent("modify test");

        var result = jsonTaskRepository.update(lastTaskToModify);
        // Then
        assert Objects.equals(jsonTaskRepository.findById(result.getId()).get().getContent(), "modify test");

    }


}
