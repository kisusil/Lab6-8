package ru.lab6.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.common.response.Response;

import java.time.LocalDateTime;
import java.util.List;

public class ParserResponse {
    public String parse(Response response) {
        if (response.getStatus().equals("error")) {
            return response.getDescription();
        }
        if (response.getStatus().equals("ok small")) {
            return response.getDescription();
        }
        if (response.getStatus().equals("ok big")) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonDeserializer())
                    .create();

            List<HumanBeing> list = gson.fromJson(response.getDescription(), new TypeToken<List<HumanBeing>>() {}.getType());
            StringBuilder result = new StringBuilder("Количество: " + list.size() + "\n");

            list.forEach(humanBeing -> {
                result.append("id ").append(humanBeing.getId()).append("\n");
                result.append("name ").append(humanBeing.getName()).append("\n");
                result.append("coordinateX ").append(humanBeing.getCoordinates().getX()).append("\n");
                result.append("coordinateY ").append(humanBeing.getCoordinates().getY()).append("\n");
                result.append("creationDate ").append(humanBeing.getCreationDate().toString()).append("\n");
                result.append("realHero ").append(humanBeing.getRealHero()).append("\n");
                result.append("hasToothPick ").append(humanBeing.getHasToothPick()).append("\n");
                result.append("impactSpeed ").append(humanBeing.getImpactSpeed()).append("\n");
                result.append("minutesOfWaiting ").append(humanBeing.getMinutesOfWaiting()).append("\n");
                result.append("weaponType ").append(humanBeing.getWeaponType()).append("\n");
                result.append("mood ").append(humanBeing.getMood()).append("\n");
                result.append("carName ").append(humanBeing.getCar().getName()).append("\n");
                result.append("\n");
            });

            return result.toString();
        }

        throw new RuntimeException("Неизвестный статус");
    }
}
