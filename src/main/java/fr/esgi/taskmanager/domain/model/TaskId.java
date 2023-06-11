package fr.esgi.taskmanager.domain.model;

import java.util.Objects;

public class TaskId {
    private Integer value;

    public TaskId(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskId taskId = (TaskId) o;
        return Objects.equals(value, taskId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
