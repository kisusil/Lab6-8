package ru.lab6.common.request;

import com.google.gson.Gson;
import ru.lab6.common.parameters.Parameters;

public class Request {
    private String command_name;
    private String parameters;

    public Request(String command_name, String parameters){
        this.command_name = command_name;
        this.parameters = parameters;
    }

    public String json(){
        return new Gson().toJson(this);
    }
}
