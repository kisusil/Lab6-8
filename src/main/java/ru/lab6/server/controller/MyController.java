package ru.lab6.server.controller;

import ru.lab6.common.humanbeing.Car;
import ru.lab6.common.humanbeing.Coordinates;
import ru.lab6.common.humanbeing.Mood;
import ru.lab6.common.humanbeing.WeaponType;
import ru.lab6.common.parameters.*;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.ApplicationContext;

public class MyController implements Controller {
    private final ApplicationContext applicationContext;

    public MyController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public Response add (String name,
                         Coordinates coordinates,
                         Boolean realHero,
                         boolean hasToothpick,
                         float impactSpeed,
                         Long minutesOfWaiting,
                         WeaponType weaponType,
                         Mood mood,
                         Car car) {
        CreationParameters parameters = new CreationParameters ();

        parameters.name = name;
        parameters.car = car;
        parameters.coordinates = coordinates;
        parameters.realHero = realHero;
        parameters.hasToothpick = hasToothpick;
        parameters.impactSpeed = impactSpeed;
        parameters.minutesOfWaiting = minutesOfWaiting;
        parameters.weaponType = weaponType;
        parameters.mood = mood;

        return applicationContext.getCommands().get("add").execute(parameters);
    }

    @Override
    public Response clear() {
        EmptyParameters parameters = new EmptyParameters();

        return applicationContext.getCommands().get("clear").execute(parameters);
    }

    @Override
    public Response executeScript(String fileName) {
        ExecuteScriptParameters parameters = new ExecuteScriptParameters();

        parameters.fileName = fileName;

        return applicationContext.getCommands().get("executeScript").execute(parameters);
    }

    @Override
    public Response info() {
        EmptyParameters parameters = new EmptyParameters();

        return applicationContext.getCommands().get("info").execute(parameters);
    }

    @Override
    public Response  removeById(int id) {
        IdParameters parameters = new IdParameters();

        parameters.id = id;

        return applicationContext.getCommands().get("removeById").execute(parameters);
    }

    @Override
    public Response removeLower(String name,
                                Coordinates coordinates,
                                Boolean realHero,
                                boolean hasToothpick,
                                float impactSpeed,
                                Long minutesOfWaiting,
                                WeaponType weaponType,
                                Mood mood,
                                Car car) {
        CreationParameters parameters = new CreationParameters();

        parameters.name = name;
        parameters.car = car;
        parameters.coordinates = coordinates;
        parameters.realHero = realHero;
        parameters.hasToothpick = hasToothpick;
        parameters.impactSpeed = impactSpeed;
        parameters.minutesOfWaiting = minutesOfWaiting;
        parameters.weaponType = weaponType;
        parameters.mood = mood;

        return applicationContext.getCommands().get("removeLower").execute(parameters);
    }

    @Override
    public Response save() {
        EmptyParameters parameters = new EmptyParameters();

        return applicationContext.getCommands().get("save").execute(parameters);
    }

    @Override
    public Response show() {
        EmptyParameters parameters = new EmptyParameters();

        return applicationContext.getCommands().get("show").execute(parameters);
    }

    @Override
    public Response update(int id, String name, Integer coordinateX, double coordinateY, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, String carName) {
        UpdateParameters parameters = new UpdateParameters();

        parameters.id = id;
        parameters.name = name;
        parameters.carName = carName;
        parameters.coordinateX = coordinateX;
        parameters.coordinateY = coordinateY;
        parameters.realHero = realHero;
        parameters.hasToothpick = hasToothpick;
        parameters.impactSpeed = impactSpeed;
        parameters.minutesOfWaiting = minutesOfWaiting;
        parameters.weaponType = weaponType;
        parameters.mood = mood;

        return applicationContext.getCommands().get("update").execute(parameters);
    }

    @Override
    public Response addIfMax(String name,
                             Coordinates coordinates,
                             Boolean realHero,
                             boolean hasToothpick,
                             float impactSpeed,
                             Long minutesOfWaiting,
                             WeaponType weaponType,
                             Mood mood,
                             Car car) {
        CreationParameters parameters = new CreationParameters();

        parameters.name = name;
        parameters.car = car;
        parameters.coordinates = coordinates;
        parameters.realHero = realHero;
        parameters.hasToothpick = hasToothpick;
        parameters.impactSpeed = impactSpeed;
        parameters.minutesOfWaiting = minutesOfWaiting;
        parameters.weaponType = weaponType;
        parameters.mood = mood;

        return applicationContext.getCommands().get("addIfMax").execute(parameters);
    }

    @Override
    public Response countByMood(Mood mood) {
        MoodParameters parameters = new MoodParameters();
        parameters.mood = mood;

        return applicationContext.getCommands().get("countByMood").execute(parameters);
    }

    @Override
    public Response filterGreaterThanMood(Mood mood) {
        MoodParameters parameters = new MoodParameters();
        parameters.mood = mood;

        return applicationContext.getCommands().get("filterGreaterThanMood").execute(parameters);
    }

    @Override
    public Response printAscending() {
        EmptyParameters parameters = new EmptyParameters();

        return applicationContext.getCommands().get("printAscending").execute(parameters);
    }

    @Override
    public Response help() {
        EmptyParameters parameters = new EmptyParameters();

        return applicationContext.getCommands().get("help").execute(parameters);
    }
}
