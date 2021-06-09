package ru.lab6.server.model.command;

import ru.lab6.common.parameters.IdParameters;
import ru.lab6.common.parameters.LoginParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.collection.RepositoryException;

public class RemoveByIdCommand implements Command {
    private final ApplicationContext applicationContext;

    public RemoveByIdCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public Response execute(Parameters parameters) {
        if (!(parameters instanceof IdParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        IdParameters idParameters = (IdParameters) parameters;

        LoginParameters loginParameters = new LoginParameters();
        loginParameters.login = idParameters.login;
        loginParameters.password = idParameters.password;
        Response response = applicationContext.getCommands().get("login").execute(loginParameters);

        if (!response.getStatus().equals("ok")) {
            return response;
        }

        try {
            applicationContext.getRepository().delete(idParameters.id);
            return new Response().setEmptyResult();
        }
        catch (RepositoryException e){
            return new Response().setErrorResponse("человека с таким id не существует", "");
        }
    }
}
