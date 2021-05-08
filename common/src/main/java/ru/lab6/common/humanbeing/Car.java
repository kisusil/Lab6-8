package ru.lab6.common.humanbeing;

public class Car {
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
