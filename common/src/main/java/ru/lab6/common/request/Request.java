package ru.lab6.common.request;

import com.google.gson.Gson;

public class Request {
    private String commandName;
    private String parameters;

    public Request(String commandName, String parameters){
        this.commandName = commandName;
        this.parameters = parameters;
    }

    public String json(){
        return new Gson().toJson(this);
    }
}
