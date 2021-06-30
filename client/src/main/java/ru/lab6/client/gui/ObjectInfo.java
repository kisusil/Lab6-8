package ru.lab6.client.gui;

import java.util.Arrays;
import java.util.List;

public class ObjectInfo {
    private static final List<String> fields = Arrays.asList("id",
            "name",
            "x",
            "y",
            "creationDate",
            "realHero",
            "hasToothpick",
            "impactSpeed",
            "minutesOfWaiting",
            "weaponType",
            "mood",
            "carName");

    public static List<String> getFields(){
        return fields;
    }
}
