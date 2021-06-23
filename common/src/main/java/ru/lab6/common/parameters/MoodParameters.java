package ru.lab6.common.parameters;

import ru.lab6.common.humanbeing.Mood;

import java.io.Serializable;

public class MoodParameters implements Parameters, Serializable {
    public Mood mood;
    public String login;
    public String password;
}
