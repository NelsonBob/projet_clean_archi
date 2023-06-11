package fr.esgi.taskmanager.kernel;

@FunctionalInterface
public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}
