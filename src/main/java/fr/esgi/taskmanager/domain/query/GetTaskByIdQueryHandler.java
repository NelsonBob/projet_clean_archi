package fr.esgi.taskmanager.domain.query;

import fr.esgi.taskmanager.domain.exceptions.TaskNotFoundException;
import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.domain.model.TaskId;
import fr.esgi.taskmanager.domain.repository.TaskRepository;
import fr.esgi.taskmanager.kernel.QueryHandler;

public class GetTaskByIdQueryHandler implements QueryHandler<GetTaskByIdQuery, Task> {
    private final TaskRepository repository;

    public GetTaskByIdQueryHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task handle(GetTaskByIdQuery query) {
        return repository.findById(new TaskId(query.taskId()))
                .orElseThrow(() -> new TaskNotFoundException(query.taskId()));
    }
}
