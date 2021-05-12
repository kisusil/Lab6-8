package ru.lab6.server.model.command;

import ru.lab6.common.EmptyParameters;
import ru.lab6.common.Parameters;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.CollectionInfo;

public class InfoCommand implements Command {
    private final ApplicationContext applicationContext;

    public InfoCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String execute (Parameters parameters) {
        if (!(parameters instanceof EmptyParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        CollectionInfo collectionInfo = applicationContext.getRepository().getInfo();
        return collectionInfo.toString();
    }
}