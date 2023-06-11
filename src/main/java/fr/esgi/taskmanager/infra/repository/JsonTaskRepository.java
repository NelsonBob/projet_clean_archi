package fr.esgi.taskmanager.infra.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.domain.model.TaskId;
import fr.esgi.taskmanager.domain.repository.TaskRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class JsonTaskRepository implements TaskRepository {
    private Map<TaskId,Task> tasks = new HashMap<>();
    private String filename;
    private Path dataDir;
    @Override
    public TaskId nextIdentity() {
        return new TaskId(findNextMissingKey(tasks));
    }
    public JsonTaskRepository(String filename) throws IOException {
        this.filename = filename;
        this.dataDir = Path.of(System.getProperty("user.home"), ".consoleAgenda");
        Files.createDirectories(dataDir);
        loadTasksFromFile();
    }
    @Override
    public Optional<Task> findById(TaskId id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public List<Task> findAll() {
        return List.copyOf(tasks.values());
    }

    @Override
    public Task save(Task entity) {
        entity.setId(nextIdentity());
        tasks.put(entity.getId(),entity);
        saveTasksToFile();
        return tasks.get(entity.getId());
    }

    @Override
    public Task update(Task entity) {
        tasks.put(entity.getId(),entity);
        saveTasksToFile();
        return tasks.get(entity.getId());
    }

    @Override
    public void delete(TaskId id) {
        tasks.remove(id);
        saveTasksToFile();
    }

    private void loadTasksFromFile() {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(dataDir.toString(), filename)));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Task[] taskArray = gson.fromJson(jsonString, Task[].class);
            for (Task task : taskArray
                 ) {
                tasks.put(task.getId(), task);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTasksToFile() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(tasks.values());
            Files.write(Paths.get(dataDir.toString(), filename), jsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int findNextMissingKey(Map<TaskId, ?> map) {
        List<Integer> sortedKeys = new ArrayList<>();
        for (TaskId key : map.keySet()) {
            sortedKeys.add(key.getValue());
        }
        Collections.sort(sortedKeys);
        int lastKey = 0;
        for (int key : sortedKeys) {
            if (key - lastKey > 1) {
                return lastKey + 1;
            }
            lastKey = key;
        }
        return lastKey + 1;
    }
}
