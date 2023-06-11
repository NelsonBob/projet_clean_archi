package fr.esgi.taskmanager.domain.command;

import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.domain.repository.TaskRepository;
import fr.esgi.taskmanager.kernel.CommandHandler;

public class AddTaskCommandHandler implements CommandHandler<AddTaskCommand, Task> {
    private final TaskRepository repository;

    public AddTaskCommandHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task handle(AddTaskCommand command) {
        var task = new Task(command.content(),command.dueDate());
        return repository.save(task);
    }
}
