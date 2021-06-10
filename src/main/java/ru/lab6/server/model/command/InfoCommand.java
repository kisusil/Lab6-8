package ru.lab6.server.model.command;

import ru.lab6.common.parameters.EmptyParameters;
import ru.lab6.common.parameters.LoginParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.collection.CollectionInfo;

public class InfoCommand implements Command {
    private final ApplicationContext applicationContext;

    public InfoCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Response execute (Parameters parameters) {
        if (!(parameters instanceof EmptyParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        EmptyParameters emptyParameters = (EmptyParameters) parameters;

        LoginParameters loginParameters = new LoginParameters();
        loginParameters.login = emptyParameters.login;
        loginParameters.password = emptyParameters.password;
        Response response = applicationContext.getCommands().get("login").execute(loginParameters);

        if (!response.getStatus().equals("ok")) {
            return response;
        }

        CollectionInfo collectionInfo = applicationContext.getRepository().getInfo();
        return new Response().setStringResult(collectionInfo.toString());
    }
}