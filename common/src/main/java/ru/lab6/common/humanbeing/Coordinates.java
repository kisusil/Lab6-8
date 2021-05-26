package ru.lab6.common.humanbeing;

import java.awt.*;
import java.io.Serializable;

public class Coordinates implements Serializable {
    private Integer x; //Значение поля должно быть больше -475, Поле не может быть null
    private double y; //Значение поля должно быть больше -533

    public Coordinates(Integer x, double y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
