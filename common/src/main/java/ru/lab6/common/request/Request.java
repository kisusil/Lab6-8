package ru.lab6.common.request;

import ru.lab6.common.parameters.Parameters;

public class Request {
    private String command_name;
    private Parameters parameters;

    Request(String command_name, Parameters parameters){
        this.command_name = command_name;
        this.parameters = parameters;
    }

    public String json(){
        return null;
    }
}
