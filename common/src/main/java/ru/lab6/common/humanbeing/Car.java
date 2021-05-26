package ru.lab6.common.humanbeing;

import java.io.Serializable;

public class Car implements Serializable {
    private String name; //Поле может быть null

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
