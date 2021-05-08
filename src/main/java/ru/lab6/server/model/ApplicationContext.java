package ru.lab6.server.model;

import ru.lab6.server.CollectionSaver;
import ru.lab6.server.model.command.Command;

import java.util.Map;

public class ApplicationContext {
    private final HumanBeingBuilder humanBeingBuilder;
    private final Repository repository;
    private Map <String, Command> commands;
    private final CollectionSaver collectionSaver;

    public ApplicationContext(HumanBeingBuilder humanBeingBuilder, Repository repository, CollectionSaver collectionSaver) {
        this.humanBeingBuilder = humanBeingBuilder;
        this.repository = repository;
        this.collectionSaver = collectionSaver;
    }


    public CollectionSaver getCollectionSaver() {
        return collectionSaver;
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
