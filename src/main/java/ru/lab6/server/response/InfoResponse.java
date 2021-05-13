package ru.lab6.server.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.lab6.server.LocalDateTimeJsonSerializer;

import java.time.LocalDateTime;

public class InfoResponse implements Response {
    private String collection_type;
    private LocalDateTime init_date;
    private int students_count;

    InfoResponse(String collection_type, LocalDateTime init_date, int students_count){
        this.collection_type = collection_type;
        this.init_date = init_date;
        this.students_count = students_count;
    }

    public String json(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonSerializer());
        Gson gson = gsonBuilder.create();
        return gson.toJson(gson);
    }
}
