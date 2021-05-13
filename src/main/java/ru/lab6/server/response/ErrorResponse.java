package ru.lab6.server.response;

public class ErrorResponse implements Response{
    private String error_name;
    private String error_description;


    public String json() {
        return null;
    }

    //какая-то логика
}
