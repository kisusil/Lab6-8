package ru.lab6.server.model.command;

import ru.lab6.common.parameters.EmptyParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.ApplicationContext;

public class ClearCommand implements Command {
    private final ApplicationContext applicationContext;

    public ClearCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Response execute (Parameters parameters) {
        if (!(parameters instanceof EmptyParameters)) {
            return new Response().setErrorResponse("ошибка параметров команды", "");
        } else {
            applicationContext.getRepository().deleteAll();
            return new Response().setEmptyResult();
        }
    }
}