package fr.esgi.taskmanager.domain.command;

import fr.esgi.taskmanager.kernel.Command;

import java.time.LocalDate;
import java.util.Objects;

public record AddTaskCommand(String content, LocalDate dueDate) implements Command {
        public AddTaskCommand {
                Objects.requireNonNull(content, "content must not be null");
                Objects.requireNonNull(dueDate, "dueDate must not be null");
        }
}
