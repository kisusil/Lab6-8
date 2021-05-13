package ru.lab6.common.parameters;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.lab6.common.humanbeing.Mood;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MoodJsonSerializer implements JsonSerializer<Mood> {
    @Override
    public JsonElement serialize(Mood mood, Type typeOfSrc, JsonSerializationContext context) {
        Map<Mood, String> moods = new HashMap<Mood, String>(){{
            put(Mood.SADNESS, "sadness");
            put(Mood.LONGING, "longing");
            put(Mood.CALM, "calm");
            put(Mood.FRENZY, "frenzy");
        }
        };
        return context.serialize(moods.get(mood));
    }
}