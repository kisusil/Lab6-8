package ru.lab6.server.model;

import ru.lab6.server.database.users.UserDao;
import ru.lab6.server.model.collection.HumanBeingBuilder;
import ru.lab6.server.model.collection.Repository;
import ru.lab6.server.model.command.Command;

import java.util.Map;

public class ApplicationContext {
    private final HumanBeingBuilder humanBeingBuilder;
    private final Repository repository;
    private Map <String, Command> commands;
    private UserDao userDao;

    public ApplicationContext(HumanBeingBuilder humanBeingBuilder, Repository repository, UserDao userDao) {
        this.humanBeingBuilder = humanBeingBuilder;
        this.repository = repository;
        this.userDao = userDao;
    }

    public UserDao getUserDao(){
        return userDao;
    }

    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }

    public Map<String, Command> getCommands() { return commands; }

    public HumanBeingBuilder getHumanBeingBuilder() {
        return humanBeingBuilder;
    }

    public Repository getRepository() {
        return repository;
    }
}
