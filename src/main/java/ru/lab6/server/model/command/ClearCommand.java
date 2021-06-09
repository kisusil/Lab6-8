package ru.lab6.server.model.command;

import ru.lab6.common.parameters.CreationParameters;
import ru.lab6.common.parameters.EmptyParameters;
import ru.lab6.common.parameters.LoginParameters;
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
            applicationContext.getUserDao().deleteAll();
            return new Response().setErrorResponse("ошибка параметров команды", "");
        } else {

            EmptyParameters emptyParameters = (EmptyParameters) parameters;

            LoginParameters loginParameters = new LoginParameters();
            loginParameters.login = emptyParameters.login;
            loginParameters.password = emptyParameters.password;
            Response response = applicationContext.getCommands().get("login").execute(loginParameters);

            if (!response.getStatus().equals("ok")) {
                return response;
            }

            applicationContext.getRepository().deleteAll();
            return new Response().setEmptyResult();
        }
    }
}