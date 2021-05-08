package ru.lab6.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.lab6.common.Request;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class RequestJsonSerializer implements JsonSerializer<Request>{
    @Override
    public JsonElement serialize(Request request, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(request.toString());
    }
}
