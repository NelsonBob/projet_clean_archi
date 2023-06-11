package fr.esgi.taskmanager.domain.command;

import fr.esgi.taskmanager.domain.model.TaskStatus;
import fr.esgi.taskmanager.kernel.Command;

import java.time.LocalDate;
import java.util.Objects;

public record UpdateTaskCommand(Integer taskId, String content, LocalDate dueDate, TaskStatus status) implements Command {
    public UpdateTaskCommand {
        Objects.requireNonNull(taskId, "taskId must not be null");
    }
}