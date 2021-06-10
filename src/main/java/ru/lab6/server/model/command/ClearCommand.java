package ru.lab6.server.model.command;

import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.common.parameters.EmptyParameters;
import ru.lab6.common.parameters.LoginParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.collection.RepositoryException;

import java.util.List;
import java.util.stream.Collectors;

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

            List<HumanBeing> humanBeings = applicationContext.getRepository()
                    .getAll()
                    .stream()
                    .filter(humanBeing -> humanBeing.getUser().getLogin().equals(emptyParameters.login))
                    .collect(Collectors.toList());

            if (humanBeings.isEmpty()) {
                return new Response().setStringResult("Удалять нечего");
            }

            for (HumanBeing humanBeing : humanBeings) {
                try {
                    applicationContext.getRepository().delete(humanBeing.getId());
                } catch (RepositoryException e) {
                    throw  new RuntimeException("Что-то пошло не так (такой ошибки быть не может)");
                }
            }
            return new Response().setEmptyResult();
        }
    }
}