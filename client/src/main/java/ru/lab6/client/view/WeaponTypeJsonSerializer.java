package ru.lab6.client.view;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.lab6.common.humanbeing.WeaponType;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class WeaponTypeJsonSerializer implements JsonSerializer<WeaponType> {
    @Override
    public JsonElement serialize(WeaponType weaponType, Type typeOfSrc, JsonSerializationContext context) {
        Map<WeaponType, String> weapons = new HashMap<WeaponType, String>(){{
            put(WeaponType.AXE, "axe");
            put(WeaponType.KNIFE, "knife");
            put(WeaponType.MACHINE_GUN, "machine_gun");
            put(WeaponType.SHOTGUN, "shotgun");
        }
        };
        return context.serialize(weapons.get(weaponType));
    }
}
