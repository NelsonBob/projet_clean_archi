package fr.esgi.taskmanager.domain.command;

import fr.esgi.taskmanager.domain.exceptions.TaskNotFoundException;
import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.domain.model.TaskId;
import fr.esgi.taskmanager.domain.repository.TaskRepository;
import fr.esgi.taskmanager.kernel.CommandHandler;

public class UpdateTaskCommandHandler implements CommandHandler<UpdateTaskCommand, Task> {
    private final TaskRepository repository;

    public UpdateTaskCommandHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task handle(UpdateTaskCommand command) {
        var task = repository.findById(new TaskId(command.taskId())).orElseThrow(() -> new TaskNotFoundException(command.taskId()));
        if(command.content() != null ) task.setContent(command.content());
        if(command.dueDate() != null ) task.setDueDate(command.dueDate());
        if(command.status() != null ) task.setStatus(command.status());
        return repository.update(task);
    }
}
