package ru.lab6.server.model.command;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.response.Response;

public interface Command {
    Response execute (Parameters parameters);
}
