package ru.lab6.common.request;

import com.google.gson.Gson;
import ru.lab6.common.parameters.Parameters;

import java.io.Serializable;

public class Request implements Serializable {
    private final String commandName;
    private final Parameters parameters;

    public Request(String commandName, Parameters parameters){
        this.commandName = commandName;
        this.parameters = parameters;
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
}
