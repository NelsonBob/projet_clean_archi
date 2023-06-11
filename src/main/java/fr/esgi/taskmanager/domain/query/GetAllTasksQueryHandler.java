package fr.esgi.taskmanager.domain.query;

import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.domain.repository.TaskRepository;
import fr.esgi.taskmanager.kernel.QueryHandler;

import java.util.Comparator;
import java.util.List;

public class GetAllTasksQueryHandler implements QueryHandler<GetAllTasksQuery,List<Task>> {
    private final TaskRepository repository;

    public GetAllTasksQueryHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> handle(GetAllTasksQuery query) {
        var tasks = repository.findAll();
        //Order tasks by creation date
        tasks.sort(Comparator.comparing(Task::getCreationDate).reversed());
        return tasks;
    }
}
