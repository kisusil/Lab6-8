package ru.lab6.server.response;

public class HelpResponse implements Response{
    private String help;

    HelpResponse(String help){
        this.help = help;
    }

    public String json() {
        return null;
    }
}
