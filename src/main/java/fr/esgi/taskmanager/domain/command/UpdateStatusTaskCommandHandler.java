package fr.esgi.taskmanager.domain.command;


import fr.esgi.taskmanager.domain.exceptions.TaskNotFoundException;
import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.domain.model.TaskId;
import fr.esgi.taskmanager.domain.repository.TaskRepository;
import fr.esgi.taskmanager.kernel.CommandHandler;

public class UpdateStatusTaskCommandHandler implements CommandHandler<UpdateStatusTaskCommand, Task> {
    private final TaskRepository repository;

    public UpdateStatusTaskCommandHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task handle(UpdateStatusTaskCommand command) {
        var task = repository.findById(new TaskId(command.taskId())).orElseThrow(() -> new TaskNotFoundException(command.taskId()));
        if(command.status() != null ) task.setStatus(command.status());
        return repository.update(task);
    }
}
