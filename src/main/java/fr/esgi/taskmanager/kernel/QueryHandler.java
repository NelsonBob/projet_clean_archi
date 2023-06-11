package fr.esgi.taskmanager.kernel;

@FunctionalInterface
public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}