package ru.lab6.common.humanbeing;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "human_beings")
public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Column(name = "creation_date")
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Column(name = "real_hero")
    private Boolean realHero; //Поле не может быть null
    @Column(name = "has_toothpick")
    private boolean hasToothpick;
    @Column(name = "impact_speed")
    private float impactSpeed;
    @Column(name = "minutes_of_waiting")
    private Long minutesOfWaiting; //Поле может быть null
    @Column(name = "weapon_type")
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле может быть null

    @OneToOne(mappedBy = "humanBeing", cascade = CascadeType.ALL, orphanRemoval = true)
    private Car car; //Поле может быть null
    @OneToOne(mappedBy = "humanBeing", cascade = CascadeType.ALL, orphanRemoval = true)
    private Coordinates coordinates; //Поле не может быть null

    protected HumanBeing() {}

    public HumanBeing(int id, String name, Coordinates coordinates, LocalDateTime creationDate, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    public boolean isHasToothpick() {
        return hasToothpick;
    }

    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    @Override
    public int compareTo(HumanBeing o) {
        return (int) (this.getImpactSpeed() - o.getImpactSpeed());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public void setRealHero(Boolean realHero) {
        this.realHero = realHero;
    }

    public boolean getHasToothPick() {
        return hasToothpick;
    }

    public float getImpactSpeed() {
        return impactSpeed;
    }

    public void setImpactSpeed(float impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public Long getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public void setMinutesOfWaiting(Long minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
