package ru.lab6.common.parameters;

import ru.lab6.common.humanbeing.Mood;
import ru.lab6.common.humanbeing.WeaponType;

import java.io.Serializable;

public class UpdateParameters implements Parameters, Serializable {
    public int id;
    public String name;
    public Boolean realHero;
    public boolean hasToothpick;
    public float impactSpeed;
    public Long minutesOfWaiting;
    public WeaponType weaponType;
    public Mood mood;
    public Integer coordinateX;
    public double coordinateY;
    public String carName;
    public String login;
    public String password;

}
