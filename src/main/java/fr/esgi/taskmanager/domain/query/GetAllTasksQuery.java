package fr.esgi.taskmanager.domain.query;

import fr.esgi.taskmanager.domain.model.TaskId;
import fr.esgi.taskmanager.kernel.Query;

import java.util.Objects;

public class GetAllTasksQuery implements Query{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return o instanceof GetAllTasksQuery;
    }

    @Override
    public int hashCode() {
        return Objects.hash(1);
    }
}