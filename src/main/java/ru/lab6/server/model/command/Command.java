package ru.lab6.server.model.command;
import ru.lab6.common.parameters.Parameters;

public interface Command {
    String execute (Parameters parameters);
}
