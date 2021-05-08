package ru.lab6.server.model.command;

import ru.lab6.server.model.ApplicationContext;

public class PrintAscendingCommand implements Command {
    private final ApplicationContext applicationContext;

    public PrintAscendingCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String execute(Parameters parameters) {
        if (!(parameters instanceof EmptyParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        return applicationContext.getCommands().get("show").execute(parameters);
    }
}
