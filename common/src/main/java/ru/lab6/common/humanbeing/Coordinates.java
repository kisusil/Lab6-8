package ru.lab6.common.humanbeing;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;

@Entity
@Table(name = "coordinates")
public class Coordinates implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "humanbeing_id")
    private HumanBeing humanBeing;

    private Integer x; //Значение поля должно быть больше -475, Поле не может быть null
    private double y; //Значение поля должно быть больше -533

    protected Coordinates(){}
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
