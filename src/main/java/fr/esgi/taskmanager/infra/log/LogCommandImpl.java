package fr.esgi.taskmanager.infra.log;

import fr.esgi.taskmanager.domain.log.LogCommand;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogCommandImpl implements LogCommand {
    private final String logFilePath;

    public LogCommandImpl() {
        this.logFilePath = System.getProperty("user.home") + "/.consoleagenda/log.txt";
    }

    public void log(String command, String[] args, String errorMessage) {
        String status = errorMessage == null ? "[ok+]" : "[err]";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH'h'mm','ss"));
        String logEntry = String.format("%s[%s] %s : %s\n", status, timestamp, command, errorMessage == null ? "" : errorMessage);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            if (command.equals("list")) {
                String[] taskList = args[0].split("\\n");
                for (String task : taskList) {
                    String[] taskInfo = task.split("\\|");
                    String dueDateStr = taskInfo[1].trim();
                    LocalDateTime dueDate = LocalDateTime.parse(dueDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    if (dueDate.isBefore(LocalDateTime.now())) {
                        logEntry = "\u001B[31m" + logEntry + "\u001B[0m"; // add ANSI color code for red
                        break; // only mark the first overdue task as red
                    }
                }
            }
            writer.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}