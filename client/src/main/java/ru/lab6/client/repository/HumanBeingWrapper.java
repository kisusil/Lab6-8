package ru.lab6.client.repository;

import javafx.beans.property.*;
import ru.lab6.common.humanbeing.HumanBeing;

public class HumanBeingWrapper {
    private final HumanBeing humanBeing;

    private SimpleIntegerProperty id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private SimpleStringProperty name; //Поле не может быть null, Строка не может быть пустой
    private SimpleStringProperty creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private SimpleBooleanProperty realHero; //Поле не может быть null
    private SimpleBooleanProperty hasToothpick;
    private SimpleFloatProperty impactSpeed;
    private SimpleLongProperty minutesOfWaiting; //Поле может быть null
    private SimpleStringProperty weaponType; //Поле может быть null
    private SimpleStringProperty mood; //Поле может быть null
    private SimpleStringProperty carName; //Поле может быть null
    private SimpleIntegerProperty x; //Значение поля должно быть больше -475, Поле не может быть null
    private SimpleDoubleProperty y; //Значение поля должно быть больше -533

    public HumanBeingWrapper(HumanBeing humanBeing) {
        this.humanBeing = humanBeing;
        this.id = new SimpleIntegerProperty(humanBeing.getId());
        this.name = new SimpleStringProperty(humanBeing.getName());
        this.creationDate = new SimpleStringProperty(humanBeing.getCreationDate().toString());
        this.realHero = new SimpleBooleanProperty(humanBeing.getRealHero());
        this.hasToothpick = new SimpleBooleanProperty(humanBeing.getHasToothPick());
        this.impactSpeed = new SimpleFloatProperty(humanBeing.getImpactSpeed());
        this.minutesOfWaiting = new SimpleLongProperty(humanBeing.getMinutesOfWaiting());
        this.weaponType = new SimpleStringProperty(humanBeing.getWeaponType().name());
        this.mood = new SimpleStringProperty(humanBeing.getMood().name());
        this.carName = new SimpleStringProperty(humanBeing.getCar().getName());
        this.x = new SimpleIntegerProperty(humanBeing.getCoordinates().getX());
        this.y = new SimpleDoubleProperty(humanBeing.getCoordinates().getY());
    }


    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getCreationDate() {
        return creationDate.get();
    }

    public boolean isRealHero() {
        return realHero.get();
    }

    public boolean isHasToothpick() {
        return hasToothpick.get();
    }

    public float getImpactSpeed() {
        return impactSpeed.get();
    }

    public long getMinutesOfWaiting() {
        return minutesOfWaiting.get();
    }

    public String getWeaponType() {
        return weaponType.get();
    }

    public String getMood() {
        return mood.get();
    }

    public String getCarName() {
        return carName.get();
    }

    public int getX() {
        return x.get();
    }

    public double getY() {
        return y.get();
    }
}
