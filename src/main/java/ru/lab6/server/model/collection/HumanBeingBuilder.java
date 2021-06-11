package ru.lab6.server.model.collection;

import ru.lab6.common.humanbeing.*;
import ru.lab6.common.user.User;

public interface HumanBeingBuilder {
    HumanBeing build();
    HumanBeingBuilder generateId();
    HumanBeingBuilder setName(String name);
    HumanBeingBuilder setMood(Mood mood);
    HumanBeingBuilder setCar(Car car);
    HumanBeingBuilder setCoordinates(Coordinates coordinates);
    HumanBeingBuilder setRealHero(Boolean realHero);
    HumanBeingBuilder setHasToothPick(boolean hasToothPick);
    HumanBeingBuilder setImpactSpeed(float impactSpeed);
    HumanBeingBuilder setMinutesOfWaiting(Long minutesOfWaiting);
    HumanBeingBuilder setWeaponType(WeaponType weaponType);
    HumanBeingBuilder setUser(User user);

}