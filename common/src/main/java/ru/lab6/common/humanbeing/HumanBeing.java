package ru.lab6.common.humanbeing;

import java.time.LocalDateTime;

public class HumanBeing implements Comparable<HumanBeing> {
        private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        private String name; //Поле не может быть null, Строка не может быть пустой
        private Coordinates coordinates; //Поле не может быть null
        private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        private Boolean realHero; //Поле не может быть null
        private boolean hasToothpick;
        private float impactSpeed;
        private Long minutesOfWaiting; //Поле может быть null
        private WeaponType weaponType; //Поле может быть null
        private Mood mood; //Поле может быть null
        private Car car; //Поле может быть null

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

        public Coordinates getCoordinates() {
                return coordinates;
        }

        public LocalDateTime getCreationDate() {
                return creationDate;
        }

        public Boolean getRealHero() {
                return realHero;
        }

        public boolean getHasToothPick() {
                return hasToothpick;
        }

        public float getImpactSpeed() {
                return impactSpeed;
        }

        public Long getMinutesOfWaiting() {
                return minutesOfWaiting;
        }

        public WeaponType getWeaponType() {
                return weaponType;
        }

        public Mood getMood() {
                return mood;
        }

        public Car getCar() {
                return car;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setCoordinates(Coordinates coordinates) {
                this.coordinates = coordinates;
        }

        public void setRealHero(Boolean realHero) {
                this.realHero = realHero;
        }

        public void setHasToothpick(boolean hasToothpick) {
                this.hasToothpick = hasToothpick;
        }

        public void setImpactSpeed(float impactSpeed) {
                this.impactSpeed = impactSpeed;
        }

        public void setMinutesOfWaiting(Long minutesOfWaiting) {
                this.minutesOfWaiting = minutesOfWaiting;
        }

        public void setWeaponType(WeaponType weaponType) {
                this.weaponType = weaponType;
        }

        public void setMood(Mood mood) {
                this.mood = mood;
        }

        public void setCar(Car car) {
                this.car = car;
        }
}
