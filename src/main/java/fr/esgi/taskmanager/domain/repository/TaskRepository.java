package fr.esgi.taskmanager.domain.repository;

import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.domain.model.TaskId;
import fr.esgi.taskmanager.kernel.Repository;


public interface TaskRepository extends Repository<TaskId, Task> {

}
