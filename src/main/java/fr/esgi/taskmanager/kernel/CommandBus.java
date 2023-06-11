package fr.esgi.taskmanager.kernel;

public interface CommandBus {
    <C extends Command, R> R send(C command);
}

