package ru.lab6.server.model;

import ru.lab6.common.humanbeing.*;

import java.time.LocalDateTime;

public class MyHumanBeingBuilder implements HumanBeingBuilder {
    private int availableId;
    private int id;
    private String name;
    private Coordinates coordinates;
    private Boolean realHero;
    private boolean hasToothpick;
    private float impactSpeed;
    private Long minutesOfWaiting;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;

    public MyHumanBeingBuilder(int availableId) {
        this.availableId = availableId;
    }

    @Override
    public HumanBeing build() {
        LocalDateTime creationDate = LocalDateTime.now();

        HumanBeing humanBeing = new HumanBeing (id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);

        id = 0;
        name = null;
        coordinates = null;
        realHero = null;
        hasToothpick = false;
        impactSpeed = 0;
        minutesOfWaiting = null;
        weaponType = null;
        mood = null;
        car = null;

        return humanBeing;
    }

    @Override
    public HumanBeingBuilder generateId() {
        id = availableId;
        availableId++;
        return this;
    }

    @Override
    public HumanBeingBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public HumanBeingBuilder setMood(Mood mood) {
        this.mood = mood;
        return this;
    }

    @Override
    public HumanBeingBuilder setCar(Car car) {
        this.car = car;
        return this;
    }

    @Override
    public HumanBeingBuilder setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    @Override
    public HumanBeingBuilder setRealHero(Boolean realHero) {
        this.realHero = realHero;
        return this;
    }

    @Override
    public HumanBeingBuilder setHasToothPick(boolean hasToothPick) {
        this.hasToothpick = hasToothPick;
        return this;
    }

    @Override
    public HumanBeingBuilder setImpactSpeed(float impactSpeed) {
        this.impactSpeed = impactSpeed;
        return this;
    }

    @Override
    public HumanBeingBuilder setMinutesOfWaiting(Long minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
        return this;
    }

    @Override
    public HumanBeingBuilder setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
        return this;
    }
}
