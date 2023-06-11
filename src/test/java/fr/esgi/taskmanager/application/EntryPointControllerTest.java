package fr.esgi.taskmanager.application;

import fr.esgi.taskmanager.application.exposition.EntryPointController;
import fr.esgi.taskmanager.domain.command.AddTaskCommand;
import fr.esgi.taskmanager.domain.command.RemoveTaskCommand;
import fr.esgi.taskmanager.domain.command.UpdateStatusTaskCommand;
import fr.esgi.taskmanager.domain.command.UpdateTaskCommand;
import fr.esgi.taskmanager.domain.log.LogCommand;
import fr.esgi.taskmanager.domain.model.Task;
import fr.esgi.taskmanager.domain.model.TaskStatus;
import fr.esgi.taskmanager.domain.query.GetAllTasksQuery;
import fr.esgi.taskmanager.domain.query.GetTaskByIdQuery;
import fr.esgi.taskmanager.kernel.CommandBus;
import fr.esgi.taskmanager.kernel.QueryBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


public class EntryPointControllerTest {
    private static final String ADD_COMMAND = "agenda add -c:task -d:2023-12-31";
    private static final String REMOVE_COMMAND = "agenda remove 1";
    private static final String UPDATE_COMMAND = "agenda update 1 -c:newContent -d:2024-06-30 -s:PROGRESS";
    private static final String LIST_COMMAND = "agenda list";
    private static final String GET_COMMAND = "agenda get 1";
    private static final String UPDATE_STATE_COMMAND = "agenda updatestate 1 -s:PROGRESS";
    private static final LocalDate DUE_DATE = LocalDate.of(2023, 12, 31);
    private static final TaskStatus STATUS = TaskStatus.PROGRESS;
    private EntryPointController controller;

    @Mock
    private CommandBus commandBus;
    @Mock
    private QueryBus queryBus;
    @Mock
    private LogCommand logCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new EntryPointController(commandBus, queryBus,logCommand);
    }

    @Test
    void testHandleAgendaCommand_add() {
        controller.handleAgendaCommand(ADD_COMMAND.split(" "));

        verify(commandBus).send(new AddTaskCommand("task", DUE_DATE));
    }

    @Test
    void testHandleAgendaCommand_remove() {
        controller.handleAgendaCommand(REMOVE_COMMAND.split(" "));

        verify(commandBus).send(new RemoveTaskCommand(1));
    }

    @Test
    void testHandleAgendaCommand_update() {
        controller.handleAgendaCommand(UPDATE_COMMAND.split(" "));

        verify(commandBus).send(new UpdateTaskCommand(1, "newContent", LocalDate.of(2024, 6, 30), STATUS));
    }

    @Test
    void testHandleAgendaCommand_list() {
        List<Task> tasks = Collections.singletonList(new Task("task", DUE_DATE));
        when(queryBus.send(new GetAllTasksQuery())).thenReturn(tasks);

        controller.handleAgendaCommand(LIST_COMMAND.split(" "));

        verify(queryBus).send(new GetAllTasksQuery());
    }

    @Test
    void testHandleAgendaCommand_get() {
        Task task = new Task("task", DUE_DATE);
        when(queryBus.send(new GetTaskByIdQuery(1))).thenReturn(task);

        controller.handleAgendaCommand(GET_COMMAND.split(" "));

        verify(queryBus).send(new GetTaskByIdQuery(1));
    }

    @Test
    void testHandleAgendaCommand_updateStateTask() {
        controller.handleAgendaCommand(UPDATE_STATE_COMMAND.split(" "));

        verify(commandBus).send(new UpdateStatusTaskCommand(1, STATUS));
    }
}
