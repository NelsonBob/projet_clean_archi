package fr.esgi.taskmanager.domain.command;

import fr.esgi.taskmanager.domain.exceptions.TaskNotFoundException;
import fr.esgi.taskmanager.domain.model.TaskId;
import fr.esgi.taskmanager.domain.repository.TaskRepository;
import fr.esgi.taskmanager.kernel.CommandHandler;

public class RemoveTaskCommandHandler implements CommandHandler<RemoveTaskCommand, Void> {
    private final TaskRepository repository;

    public RemoveTaskCommandHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(RemoveTaskCommand command) {
        var taskId = new TaskId(command.taskId());
        repository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(command.taskId()));
        repository.delete(taskId);
        return null;
    }
}
