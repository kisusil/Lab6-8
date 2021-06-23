package ru.lab6.common.parameters;

import java.io.Serializable;

public class ExecuteScriptParameters implements Parameters, Serializable {
    public String fileName;
    public String login;
    public String password;
}
