package fr.esgi.taskmanager.domain.command;

import fr.esgi.taskmanager.kernel.Command;

import java.util.Objects;

public record RemoveTaskCommand(Integer taskId) implements Command {
    public RemoveTaskCommand {
        Objects.requireNonNull(taskId, "taskId must not be null");
    }
}
