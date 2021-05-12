package ru.lab6.server.model.command;

import ru.lab6.common.IdParameters;
import ru.lab6.common.Parameters;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.RepositoryException;

public class RemoveByIdCommand implements Command {
    private final ApplicationContext applicationContext;

    public RemoveByIdCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public String execute(Parameters parameters) {
        if (!(parameters instanceof IdParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        IdParameters idParameters = (IdParameters) parameters;
        try {
            applicationContext.getRepository().delete(idParameters.id);
            return "Объект успешно удален";
        }
        catch (RepositoryException e){
            return "Человека с таким id не существует";
        }
    }
}
