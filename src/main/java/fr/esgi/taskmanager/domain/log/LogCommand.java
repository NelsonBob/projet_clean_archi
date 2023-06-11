package fr.esgi.taskmanager.domain.log;

public interface LogCommand {
     void log(String command, String[] args, String errorMessage) ;
}
