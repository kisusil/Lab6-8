package ru.lab6.common.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.lab6.common.humanbeing.HumanBeing;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Response implements Serializable {
    private String status;
    private String errorDescription;
    private List<HumanBeing> elements;
    private String result;


    public Response setEmptyResult(){
        this.status = "ok";
        return this;
    }

    public Response setErrorResponse(String errorName, String errorDescription){
        this.status = errorName;
        this.errorDescription = errorDescription;
        return this;
    }

    public Response setResultWithCollectionElements(List<HumanBeing> list){
        this.status = "ok";
        this.elements = list;
        return this;
    }

    public Response setStringResult(String result){
        this.status = "ok";
        this.result = result;
        return this;
    }

    public String json() {
        return new Gson().toJson(this);
    }


    public String getStatus() {
        return status;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public List<HumanBeing> getElements() {
        return elements;
    }

    public String getResult() {
        return result;
    }
}
