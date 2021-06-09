package ru.lab6.server.model.command;

import ru.lab6.common.parameters.LoginParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.response.Response;
import ru.lab6.server.database.users.User;
import ru.lab6.server.model.ApplicationContext;

public class LoginCommand implements Command {
    private final ApplicationContext applicationContext;

    public LoginCommand(ApplicationContext applicationContext) {
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
            if (user != null) {
                if (loginParameters.password.equals(user.getPassword())) {
                    return new Response().setStringResult("Welcome");
                }
            }

            return new Response().setErrorResponse("Не авторизован", "");
        }
    }
}
