package ru.lab6.common.humanbeing;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "humanbeing_id")
    private HumanBeing humanBeing;

    private String name; //Поле может быть null

    public Car(String name) {
        this.name = name;
    }
    protected Car(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
