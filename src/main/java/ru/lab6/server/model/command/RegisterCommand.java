package ru.lab6.server.model.command;

import ru.lab6.common.parameters.LoginParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.response.Response;
import ru.lab6.server.database.users.User;
import ru.lab6.server.model.ApplicationContext;

public class RegisterCommand implements Command {
    private final ApplicationContext applicationContext;

    public RegisterCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Response execute(Parameters parameters) {
        if (!(parameters instanceof LoginParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }
        else {
            LoginParameters loginParameters = (LoginParameters) parameters;
            User user = applicationContext.getUserDao().get(loginParameters.login);
            if (user == null) {
                applicationContext.getUserDao().save(new User(loginParameters.login, loginParameters.password));
                return new Response().setStringResult("Welcome");
            }

            return new Response().setErrorResponse("Пользоваьель с таким login уже существует", "");
        }
    }
}
