package ru.lab6.client.gui;

import java.util.Arrays;
import java.util.List;

public class ObjectInfo {
    private static final List<String> fields = Arrays.asList("Id",
            "Name",
            "X",
            "Y",
            "Creation date",
            "Real hero",
            "Has toothpick",
            "Impact speed",
            "Minutes of waiting",
            "Weapon type",
            "Mood",
            "Car name");

    public static List<String> getFields(){
        return fields;
    }
}
