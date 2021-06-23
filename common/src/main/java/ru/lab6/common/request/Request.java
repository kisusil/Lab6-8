package ru.lab6.common.request;

import com.google.gson.Gson;
import ru.lab6.common.parameters.Parameters;

import java.io.Serializable;

public class Request implements Serializable {
    private final String commandName;
    private final Parameters parameters;
    private final String login;
    private final String password;

    public Request(String commandName, Parameters parameters, String login, String password){
        this.commandName = commandName;
        this.parameters = parameters;
        this.login = login;
        this.password = password;
    }

    public String json(){
        return new Gson().toJson(this);
    }

    public String getCommandName() {
        return commandName;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public String getLogin() { return login; }

    public String getPassword() { return password; }
}
