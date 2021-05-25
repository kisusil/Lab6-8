package ru.lab6.common.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.lab6.common.humanbeing.HumanBeing;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Response implements Serializable {
    private final String status;
    private final String description;

    public Response(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public Response(List<HumanBeing> list) {
        this.status = "ok big";
        Gson gson =  new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonSerializer())
                .create();
        this.description = gson.toJson(list);
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String json() {
        return new Gson().toJson(this);
    }
}
