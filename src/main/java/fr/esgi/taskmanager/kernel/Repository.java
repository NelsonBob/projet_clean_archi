package fr.esgi.taskmanager.kernel;

import java.util.List;
import java.util.Optional;

public interface Repository<VOID, E> {
    VOID nextIdentity();

    Optional<E> findById(VOID id);

    List<E> findAll();

    E save(E entity);

    E update(E entity);

    void delete(VOID id);
}
