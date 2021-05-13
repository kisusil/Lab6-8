package ru.lab6.server.model.command;

import ru.lab6.common.parameters.EmptyParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.server.model.ApplicationContext;

public class ClearCommand implements Command {
    private final ApplicationContext applicationContext;

    public ClearCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String execute (Parameters parameters) {
        if (!(parameters instanceof EmptyParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        applicationContext.getRepository().deleteAll();
        return "Коллекция успешно очищена";
    }
}