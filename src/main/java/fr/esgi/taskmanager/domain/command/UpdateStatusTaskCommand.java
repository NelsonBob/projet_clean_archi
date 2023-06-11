package fr.esgi.taskmanager.domain.command;

import fr.esgi.taskmanager.domain.model.TaskStatus;
import fr.esgi.taskmanager.kernel.Command;

import java.util.Objects;

public record UpdateStatusTaskCommand(Integer taskId, TaskStatus status) implements Command {
    public UpdateStatusTaskCommand {
        Objects.requireNonNull(taskId, "taskId must not be null");
        Objects.requireNonNull(status, "status must not be null");
    }
}