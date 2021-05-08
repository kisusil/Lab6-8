package ru.lab6.server.model.command;

import ru.lab6.server.CollectionSaverException;
import ru.lab6.server.model.ApplicationContext;

public class SaveCommand implements Command {
    private final ApplicationContext applicationContext;

    public SaveCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public String execute(Parameters parameters) {
        if (!(parameters instanceof EmptyParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        try {
            applicationContext.getCollectionSaver().save(applicationContext.getRepository());
        } catch (CollectionSaverException e) {
            return e.getMessage();
        }

        return "Успешное сохранение в файл";
    }
}
