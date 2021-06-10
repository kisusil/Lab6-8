package ru.lab6.client.controller;

import ru.lab6.common.humanbeing.Car;
import ru.lab6.common.humanbeing.Coordinates;
import ru.lab6.common.humanbeing.Mood;
import ru.lab6.common.humanbeing.WeaponType;

public interface Controller {
    String add(String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car);
    String clear();
    String executeScript(String fileName);
    String info();
    String removeById(int id);
    String removeLower(String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car);
    String save();
    String show();
    String update(int id, String name, Integer coordinateX, double coordinateY, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, String  carName);
    String addIfMax(String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car);
    String countByMood(Mood mood);
    String filterGreaterThanMood(Mood mood);
    String printAscending();
    String help();
    String login(String login, String password);
    String register(String login, String password);
}
