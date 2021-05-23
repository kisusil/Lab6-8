package ru.lab6.server.model.command;

import ru.lab6.common.parameters.EmptyParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.response.Response;
import ru.lab6.server.CollectionSaverException;
import ru.lab6.server.model.ApplicationContext;

public class SaveCommand implements Command {
    private final ApplicationContext applicationContext;

    public SaveCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public Response execute(Parameters parameters) {
        if (!(parameters instanceof EmptyParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        try {
            applicationContext.getCollectionSaver().save(applicationContext.getRepository());
        } catch (CollectionSaverException e) {
            return new Response("error", e.getMessage());
        }

        return new Response("ok small", "Успешное сохранение в файл");
    }
}
