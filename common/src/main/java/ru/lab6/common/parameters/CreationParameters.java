package ru.lab6.common.parameters;

import ru.lab6.common.humanbeing.Car;
import ru.lab6.common.humanbeing.Coordinates;
import ru.lab6.common.humanbeing.Mood;
import ru.lab6.common.humanbeing.WeaponType;

import java.io.Serializable;

public class CreationParameters implements Parameters, Serializable {
    public String name;
    public Coordinates coordinates;
    public Boolean realHero;
    public boolean hasToothpick;
    public float impactSpeed;
    public Long minutesOfWaiting;
    public WeaponType weaponType;
    public Mood mood;
    public Car car;
    public String login;
    public String password;
}
