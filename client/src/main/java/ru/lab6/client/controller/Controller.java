package ru.lab6.client.controller;

import ru.lab6.common.humanbeing.Car;
import ru.lab6.common.humanbeing.Coordinates;
import ru.lab6.common.humanbeing.Mood;
import ru.lab6.common.humanbeing.WeaponType;
import ru.lab6.common.response.Response;

public interface Controller {
    Response add(String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car);
    Response clear();
    Response executeScript(String fileName);
    Response info();
    Response removeById(int id);
    Response removeLower(String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car);
    Response save();
    Response show();
    Response update(int id, String name, Integer coordinateX, double coordinateY, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, String  carName);
    Response addIfMax(String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car);
    Response countByMood(Mood mood);
    Response filterGreaterThanMood(Mood mood);
    Response printAscending();
    Response help();
    Response login(String login, String password);
    Response register(String login, String password);
}
