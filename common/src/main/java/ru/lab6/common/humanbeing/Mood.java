package ru.lab6.common.humanbeing;

public enum Mood {
    SADNESS(0),
    LONGING(1),
    CALM(2),
    FRENZY(3);

    public final int number;

    Mood(int number) {
        this.number = number;
    }
}
