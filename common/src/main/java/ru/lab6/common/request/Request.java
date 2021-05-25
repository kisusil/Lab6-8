package ru.lab6.common.request;

import com.google.gson.Gson;

import java.io.Serializable;

public class Request implements Serializable {
    private final String commandName;
    private final String parameters;

    public Request(String commandName, String parameters){
        this.commandName = commandName;
        this.parameters = parameters;
    }

    public String json(){
        return new Gson().toJson(this);
    }

    public String getCommandName() {
        return commandName;
    }

    public String getParameters() {
        return parameters;
    }
}
