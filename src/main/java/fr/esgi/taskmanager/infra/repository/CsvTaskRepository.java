package fr.esgi.taskmanager.infra.repository;

import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.domain.model.TaskId;
import fr.esgi.taskmanager.domain.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

public class CsvTaskRepository implements TaskRepository {
    @Override
    public TaskId nextIdentity() {
        return null;
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Task save(Task entity) {
        return null;
    }

    @Override
    public Task update(Task entity) {
        return null;
    }

    @Override
    public void delete(TaskId id) {

    }
}
