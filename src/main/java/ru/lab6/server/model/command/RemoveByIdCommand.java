package ru.lab6.server.model.command;

import ru.lab6.common.humanbeing.HumanBeing;
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

        HumanBeing humanBeing = applicationContext.getRepository().get(idParameters.id);

        if (humanBeing == null) {
            return new Response().setErrorResponse("Человека с таким id не существует", "");
        }

        if (!humanBeing.getUser().getLogin().equals(loginParameters.login)) {
            return new Response().setErrorResponse("Вы не можете изменять данный объект", "");
        }

        try {
            applicationContext.getRepository().delete(humanBeing.getId());
            return new Response().setEmptyResult();
        }
        catch (RepositoryException e){
            throw new RuntimeException("Что-то пошло не так (такой ошибки быть не может)");
        }
    }
}
