package ru.lab6.common.parameters;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class BooleanJsonSerializer implements JsonSerializer<Boolean> {
    @Override
    public JsonElement serialize(Boolean aBoolean, Type typeOfSrc, JsonSerializationContext context) {
        if(aBoolean){
            return new JsonPrimitive(1);
        }
        return new JsonPrimitive(0);
    }
}
